package com.example.stattrack.presentation.match



import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.stattrack.di.ServiceLocator.application
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.data.EventItems
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.floor


/**
 * [MatchViewModel] takes as parameter a repository to request data
 * that can be exposed to the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {

    /* Time component variables */
    private val duration = 30*60
    private var timeElapsed by mutableStateOf(0)
    private var finishPosition by mutableStateOf(duration)
    private var job by mutableStateOf<Job?>(null)
    private val _isCounting = MutableStateFlow(false)
    private val _timer = MutableStateFlow(getTimeElapsed())

    // Validation conponent:
    private val _teamOneCheck = MutableStateFlow(false)
    private val _teamTwoCheck = MutableStateFlow(false)

    val teamOneCheck: StateFlow<Boolean> = _teamOneCheck
    val teamTwoCheck: StateFlow<Boolean> = _teamTwoCheck




    private val _teams: MutableStateFlow<List<Team>> = MutableStateFlow(defaultTeamDummyData)
    private val _currentMatch = MutableStateFlow(
        MatchData(
            id = 0,
            creatorId = "null",
            creatorTeamId = 0,
            opponent = "Hold 2",
            matchDate = "00-00-0000",
            creatorTeamGoals = 0,
            opponentGoals = 0
    ))
    private val _allMatches = MutableStateFlow(defaultDummyMatchData)
    private val _players: MutableStateFlow<List<Player>> = MutableStateFlow(defaultDummyPlayerData)
    private val _events: MutableStateFlow<List<EventData>> = MutableStateFlow(emptyList())
    private val _playerStats: MutableStateFlow<List<PlayerStats>> = MutableStateFlow(emptyList())
    private val _startMatch = MutableStateFlow(false)

    val teams: StateFlow<List<Team>> = _teams
    val matchData: StateFlow<MatchData> = _currentMatch
    val players: StateFlow<List<Player>> = _players
    val events: StateFlow<List<EventData>> = _events
    val timer: StateFlow<String> = _timer
    val isRunning: StateFlow<Boolean> = _isCounting


    init {
        /* Fetch data from DB when init so it is ready for use later on */
        loadAllTeams()
        loadAllMatchData()
    }

    private fun startMatch(){
        _startMatch.value = true
        initMatchDateAndId()
    }

    /* Get correct ID for DB-insertion and date */
    private fun initMatchDateAndId(){
        _currentMatch.value =
            _currentMatch.value.copy(
                id = _allMatches.value.size + 1,
                matchDate = getCurrentDateTime()?:"00-00-0000"
            )
        updateMatchData(_currentMatch.value)
    }

    fun insertPlayerStats(event: EventItems){

    }

    private fun getCurrentDateTime(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "getCurrentDateTime: greater than O")
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:m a"))
        } else {
            Log.d(TAG, "getCurrentDateTime: less than O")
            val SDFormat = SimpleDateFormat("h:m a")
            SDFormat.format(Date())
        }
    }

    private fun updateMatchData(matchData: MatchData){
        /* Validation-check */
        if (
            matchData.id!=0 &&
            matchData.matchDate != "00-00-0000" &&
            matchData.creatorId != "null" &&
            matchData.creatorTeamId != 0
        ) {
            _currentMatch.value = matchData
            viewModelScope.launch {
                repository.insertMatchData(matchData)
            }
        }
    }


    fun teamOneCheck(teamOne: String){
        if (teamOne!="") {
            viewModelScope.launch {
                _teamOneCheck.value = true
            }
        }
        println(teamOne)
    }

    fun teamTwoCheck(teamTwo: String){
        if (teamTwo!="") {
            viewModelScope.launch {
                _teamTwoCheck.value = true
            }
        }
        println(teamTwo)
    }


    fun onPlayPressed(){
        if (!_isCounting.value){
            toggle()
            if (!_startMatch.value){
                /* Set matchId and matchDate */
                startMatch()
            }
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

    private fun toggle() {
        if (job == null) {
            job = MainScope().launch {
                _isCounting.value = true
                while (timeElapsed <= finishPosition ) {
                    delay(1000)
                    count()
                    _timer.value = getTimeElapsed()
                    //Log.d("Stopwatch.kt", "Counting succesfully")
                }
                if (timeElapsed == finishPosition){
                    vibratePhone()
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
        _isCounting.value = false

    }

    private fun count() {
        val next = timeElapsed + 1
        timeElapsed = next
    }

    private fun vibratePhone(){
        val vibrator = application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(2000)
        }
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
            creatorTeamId = teamId,
        )
        // Update list of players for use in EventComponent later
        getPlayersFromTeam(teamId)

    }
    fun insertEvent(event: EventItems){
        /* Insert event in Room */
        viewModelScope.launch {
            repository.insertEventData(
                EventData(
                    id = events.value.size+1,
                    eventType = event.title,
                    playerId = event.playerId,
                    playerName = event.playerName,
                    time = getTimeElapsed(),
                    matchId = _currentMatch.value.id
                )
            )
        }
        /* If event is a goal, increment goals */
        if (event.title == "Mål"){
            _currentMatch.value = _currentMatch.value.copy(
                creatorTeamGoals = _currentMatch.value.creatorTeamGoals+1
            )
            updateMatchData(_currentMatch.value)
        }
        /* Get updated values from Room */
        getEventsFromMatchId(_currentMatch.value.id)
    }

    fun setTeamTwoName(name: String){
        // Update values in model - will trigger recompose
        _currentMatch.value = _currentMatch.value.copy(
            opponent = name
        )
    }

    fun setTeamTwoScore(score: Int){
        if (score>=0) {
            _currentMatch.value = _currentMatch.value.copy(
                opponentGoals = score
            )
            updateMatchData(_currentMatch.value)
        } else Toast.makeText(application, "Score kan ikke være under 0", Toast.LENGTH_SHORT).show()
    }

    private fun getPlayersFromTeam(teamId: Int){
        viewModelScope.launch {
            repository.getAllPlayersFromTeam(teamId = teamId)
                .collect {
                    _players.value = it
                }
        }
    }


    private fun getEventsFromMatchId(matchId: Int){
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