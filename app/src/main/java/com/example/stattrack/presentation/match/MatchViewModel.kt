package com.example.stattrack.presentation.match



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.data.EventItems
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.util.*
import kotlin.math.floor


/**
 * [MatchViewModel] takes as parameter a repository to request data
 * that can be exposed to the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {

    /* Time component variables */
    //val vibrator = getSystemService(VIBRATOR_MANAGER_SERVICE)

    private val duration = 30*60
    private var timeElapsed by mutableStateOf(0)
    private var finishPosition by mutableStateOf(duration)
    private var job by mutableStateOf<Job?>(null)
    private val _isCounting = MutableStateFlow(job != null)
    private val _timer = MutableStateFlow(getTimeElapsed())


    private val _teams = MutableStateFlow(defaultTeamDummyData)
    private val _currentMatch = MutableStateFlow(defaultDummyMatchData[0])
    private val _allMatches = MutableStateFlow(defaultDummyMatchData)
    //private val _eventData = MutableStateFlow(defaultDummyEventData[0])
    private val _players = MutableStateFlow(defaultDummyPlayerData)
    private val _events = MutableStateFlow(defaultDummyEventData)
    private val _startMatch = MutableStateFlow(false)

    val teams: StateFlow<List<Team>> = _teams
    val matchData: StateFlow<MatchData> = _currentMatch
    //val eventData: StateFlow<EventData> = _eventData
    val players: StateFlow<List<Player>> = _players
    val events: StateFlow<List<EventData>> = _events
    val timer: StateFlow<String> = _timer
    val isRunning: StateFlow<Boolean> = _isCounting

    init {
        /* Fetch data from DB when init so it is ready for use later on */
        loadAllTeams()
        loadAllMatchData()
    }

    private fun toggle() {
        if (job == null) {
            job = MainScope().launch {
                while (timeElapsed <= finishPosition ) {
                    delay(1000)
                    count()
                    _timer.value = getTimeElapsed()
                    Log.d("Stopwatch.kt", "Counting succesfully")
                    println("Stopwatch.kt : Counting succesfully")
                }
                finishPosition = duration
            }
        } else {
            pause()
        }
    }

    private fun clear() {
        pause()
        timeElapsed = 0
        finishPosition = duration
    }

    private fun pause() {
        job?.cancel()
        job = null
    }

    private fun count() {
        val next = timeElapsed + 1
        timeElapsed = next
    }

    // To be called when a new match is started
    @SuppressLint("NewApi")
    private fun initMatchDateAndId(){

        _currentMatch.value =
            _currentMatch.value.copy(
                id = _allMatches.value.size + 1,
                matchDate = LocalDate.now().toString()
            )
        viewModelScope.launch {
            repository.insertMatchData(_currentMatch.value)
        }
        _startMatch.value = true
    }

    fun onPlayPressed(){
        if (!_isCounting.value){
            toggle()
        if (!_startMatch.value){
            /* Set matchId and matchDate */
            initMatchDateAndId()
        }
            /*
            while (_timer.value.isCounting){
            viewModelScope.launch {
                delay(500)
                _timerLeft.value =
                    _timer.value.timeLeftDisplay
            }
        }
         */

        }
        if (_isCounting.value){
            /* Pause */
            pause()
        }
    }

    fun onStopPressed(){
        /* Stop timer */
        clear()
        _timer.value = getTimeElapsed()
    }

    private fun getTimeElapsed(): String{
        val seconds = timeElapsed % 60
        val minutes = floor(timeElapsed.toDouble() / 60).toInt()
        val timeElapsedString =
             buildString {
                append("$minutes".padStart(2, '0'))
                append(":")
                append("$seconds".padStart(2, '0'))
            }
        return timeElapsedString
    }

    fun setTeamOneName(teamId: Int){
        // Update values in model - will trigger recompose
        _currentMatch.value = _currentMatch.value.copy(
            creatorId = teams.value[teamId-1].clubName,
            creatorTeamId = teamId
        )
        // Update list of players for use in EventComponent later
        getPlayersFromTeam(teamId)

           /* Testing purposes */
        // Log.d("setTeamOneName: ", _currentMatch.value.creatorId+_currentMatch.value.creatorTeamId)

        // Update values in repository - will not trigger recompose unless paired with a "refresh"
        /* TODO:: Can not be called before we give the _matchData an ID - will probably make sense to give it an ID when pressing PLAY on StopWatchComponent
        viewModelScope.launch {
            repository.insertMatchData(
                _currentMatch.value.copy(
                    creatorId = teams.value[teamId-1].clubName,
                    creatorTeamId = teamId
                )
            )

        }
         */
    }
    fun insertEvent(event: EventItems){
        viewModelScope.launch {
            repository.insertEventData(
                EventData(
                    id = events.value.size+1,
                    eventType = event.title,
                    playerId = event.playerId,
                    time = "03:45",
                    matchId = _currentMatch.value.id
                )
            )
        }
        getEventsFromMatchId(_currentMatch.value.id)

    }

    fun setTeamTwoName(name: String){
        // Update values in model - will trigger recompose
        _currentMatch.value = _currentMatch.value.copy(
            opponent = name
        )
    }

    fun startMatch(){
        _startMatch.value = true
        initMatchDateAndId()
    }

    fun getPlayersFromTeam(teamId: Int){
        viewModelScope.launch {
            repository.getAllPlayersFromTeam(teamId = teamId)
                .collect {
                    _players.value = it
                }
        }
    }


    fun getEventsFromMatchId(matchId: Int){
        viewModelScope.launch {
            repository.getEventDataByMatchId(matchId)
                .collect {
                    _events.value = it
                }
        }
    }

    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                _teams.value = it
            }
        }
    }

    private fun loadAllMatchData() {
        viewModelScope.launch() {
            repository.getAllMatchData().collect {
                _allMatches.value = it
            }
        }
    }
}


/*
/* cold-flow way of binding ui to viewmodel */

    val matchState: Flow<MatchViewState> = repository.getPlayerByName("asd")
        .combine(repository.getTeamByName("asda")) { player, team ->
            MatchViewState(teams = listOf(team), players = listOf(player))
        }

    val matchStateTest: Flow<MatchViewState> = repository.getAllPlayers()
        .combine(repository.getAllTeams()) { player, team ->
            MatchViewState(teams = team, players = player)
        }



    /* Hot-flow way of binding UI to ViewModel */
    private val matchViewState = MatchViewState(
        defaultTeamDummyData,
        defaultDummyPlayerData,
        defaultDummyMatchData
    )
    private val _hotMatchState = MutableStateFlow(matchStateTest)
    val hotMatchState = _hotMatchState.asStateFlow()
 --------------------------------------------------------------------------------------





  /* Read-only for the view-layer
    val viewState: StateFlow<MatchViewState> = combine(
        teams,
        players,
        matchData,
        eventData,
        playerStats
    ) { t, p, m, e, pl  ->
        MatchViewState(t, p,  m, e, pl)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MatchViewState()) */


 */


/*
  fun updateTeam(){
        viewModelScope.launch {
            repository.insertTeam(Team(1,"Hej fra databasen id: 1","UpdatedClubName","UpdatedCreator","2005","Top-top-proff"))
        }
    }

private fun loadAllPlayers() {
        viewModelScope.launch() {
            repository.getAllPlayers().collect {
                //_players.value = it
            }
        }
    }
    private fun loadAllMatchData() {
        viewModelScope.launch() {
            repository.getAllMatchData().collect {
                //_matchData.value = it
            }
        }
    }
    private fun loadAllEventData() {
        viewModelScope.launch() {
            repository.getAllEvents().collect {
                //_eventData.value = it
            }
        }
    }
    private fun loadAllPlayerStats() {
        viewModelScope.launch() {
            repository.getAllPlayerStats().collect {
                //_playerStats.value = it
            }
        }
    }*/