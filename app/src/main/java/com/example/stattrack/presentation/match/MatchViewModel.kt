package com.example.stattrack.presentation.match


import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [MatchViewModel] takes as parameter a repository to request data
 * that can be exposed by the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {
    /* cold-flow way of binding ui to viewmodel */
    /*
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

    // View-layer has no way of seeing this.
    private val _viewState = MutableStateFlow(MatchViewState())

    // Read-only for the view-layer
    val viewState: StateFlow<MatchViewState> = _viewState

    init {
        /* Fetch data from DB when init so it is ready for use later on
        *  Use viewState.value in Compose */
        loadAllTeams()
        loadAllPlayers()
        loadAllMatchData()
        loadAllEventData()
        loadAllPlayerStats()
    }

    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                _viewState.value.teams=it
            }
        }
    }
    private fun loadAllPlayers() {
        viewModelScope.launch() {
            repository.getAllPlayers().collect {
                _viewState.value.players = it
            }
        }
    }
    private fun loadAllMatchData() {
        viewModelScope.launch() {
            repository.getAllMatchData().collect {
                _viewState.value.matchData = it
            }
        }
    }
    private fun loadAllEventData() {
        viewModelScope.launch() {
            repository.getAllEvents().collect {
                _viewState.value.eventData = it
            }
        }
    }
    private fun loadAllPlayerStats() {
        viewModelScope.launch() {
            repository.getAllPlayerStats().collect {
                _viewState.value.playerStats = it
            }
        }
    }
}



