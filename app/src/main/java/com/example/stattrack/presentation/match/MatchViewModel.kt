package com.example.stattrack.presentation.match


import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.team.TeamViewState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * [MatchViewModel] takes as parameter a repository to request data
 * into a [MatchViewState] that can be exposed to the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {

    private val teams = MutableStateFlow<List<Team>>(emptyList())
    private val players = MutableStateFlow<List<Player>>(emptyList())
    private val matchData = MutableStateFlow<List<MatchData>>(emptyList())
    private val eventData = MutableStateFlow<List<EventData>>(emptyList())
    private val playerStats = MutableStateFlow<List<PlayerStats>>(emptyList())


    // Read-only for the view-layer
    val viewState: StateFlow<MatchViewState> = combine(
        teams,
        players,
        matchData,
        eventData,
        playerStats
    ) { t, p, m, e, pl  ->
        MatchViewState(t, p,  m, e, pl)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MatchViewState())

    init {
        /* Fetch data from DB when init so it is ready for use later on
        *  Use viewState.value in Compose */
        loadAllTeams()
        loadAllPlayers()
        loadAllMatchData()
        loadAllEventData()
        loadAllPlayerStats()
    }


    fun updateTeam(){
        viewModelScope.launch {
            repository.insertTeam(Team(1,"Hej fra databasen id: 1","UpdatedClubName","UpdatedCreator","2005","Top-top-proff"))
        }
    }
    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                teams.value = it
            }
        }
    }
    private fun loadAllPlayers() {
        viewModelScope.launch() {
            repository.getAllPlayers().collect {
                players.value = it
            }
        }
    }
    private fun loadAllMatchData() {
        viewModelScope.launch() {
            repository.getAllMatchData().collect {
                matchData.value = it
            }
        }
    }
    private fun loadAllEventData() {
        viewModelScope.launch() {
            repository.getAllEvents().collect {
                eventData.value = it
            }
        }
    }
    private fun loadAllPlayerStats() {
        viewModelScope.launch() {
            repository.getAllPlayerStats().collect {
                playerStats.value = it
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
 */

