package com.example.stattrack.presentation.match


import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * [MatchViewModel] takes as parameter a repository to request data
 * into a [MatchViewState] that can be exposed to the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {

    private val _teams = MutableStateFlow<List<Team>>(defaultTeamDummyData)
    private val _matchData = MutableStateFlow(defaultDummyMatchData[0])
    private val _eventData = MutableStateFlow(defaultDummyEventData[0])
    private val _players = MutableStateFlow(defaultDummyPlayerData)
    private val _events = MutableStateFlow(defaultDummyEventData)
    //private val _playerStats = MutableStateFlow<List<PlayerStats>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams
    val matchData: StateFlow<MatchData> = _matchData
    val eventData: StateFlow<EventData> = _eventData
    val players: StateFlow<List<Player>> = _players
    val events: StateFlow<List<EventData>> = _events


    init {
        /* Fetch data from DB when init so it is ready for use later on */
        loadAllTeams()
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

    fun updateTeam(){
        viewModelScope.launch {
            repository.insertTeam(Team(1,"Hej fra databasen id: 1","UpdatedClubName","UpdatedCreator","2005","Top-top-proff"))
        }
    }
    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                _teams.value = it
            }
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


