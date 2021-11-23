package com.example.stattrack.presentation.team

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch



/**
 * [TeamViewModel] takes as parameter a repository to request data and insert
 * into a [TeamViewState] that can be exposed to the [compose_teamList] flow in order
 * for the view to render the relevant information
 */

class TeamViewModel (private val repository: Repository) : ViewModel() {

    private val teams = MutableStateFlow<List<Team>>(emptyList())
    private val players = MutableStateFlow<List<Player>>(emptyList())
    private val matchData = MutableStateFlow<List<MatchData>>(emptyList())
    private val eventData = MutableStateFlow<List<EventData>>(emptyList())
    private val playerStats = MutableStateFlow<List<PlayerStats>>(emptyList())


    // Read-only for the view-layer
    val viewState: StateFlow<TeamViewState> = combine(
        teams,
        players,
        matchData,
        eventData,
        playerStats
    ) { t, p, m, e, pl  ->
        TeamViewState(t, p,  m, e, pl)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),TeamViewState())

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
        var id = (10..100).random()
        viewModelScope.launch {
        repository.insertTeam(Team(id,"Hej fra databasen id: $id","UpdatedClubName","UpdatedCreator","2005","Top-top-proff"))
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

