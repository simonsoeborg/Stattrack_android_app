package com.example.stattrack.presentation.match

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.stattrack.model.database.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * [MatchViewModel] takes as parameter a repository to request data and insert
 * into a [MatchViewState] that can be exposed by the [viewState] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {

     // View-layer has no way of seeing this.
    private val _viewState = MutableStateFlow(MatchViewState())
    // Read-only for the view-layer
    val viewState: StateFlow<MatchViewState> = _viewState

    init {
        /* Fetch data from DB when init so it is ready for use later on
        *  Use viewState.value in Compose */
        loadAllTeams()
        loadAllPlayers()
    }

    private fun loadAllTeams() {
        viewModelScope.launch {
            val loadedTeams = repository.fetchAllTeams()
            _viewState.value = _viewState.value.copy(
                teams = loadedTeams
            )
        }
    }

    private fun loadAllPlayers() {
        viewModelScope.launch {
            val loadedPlayers = repository.fetchAllPlayers()
            _viewState.value = _viewState.value.copy(
                players = loadedPlayers
            )
        }
    }

    /*
    Only used for making @Preview work in compose-files
     */
    fun loadDummyData() {
        viewModelScope.launch {
            val dummyTeams = repository.fetchDummyTeams()
            _viewState.value = _viewState.value.copy(
                teams = dummyTeams
            )
        }
        viewModelScope.launch {
            val dummyPlayers = repository.fetchDummyPlayers()
            _viewState.value = _viewState.value.copy(
                players = dummyPlayers
            )
        }
    }
}

/*
 fun fillSQLiteWithDummyData(){
        val teams: MutableList<Team> = mutableListOf()
        for (i in 1..9) {
            teams.add(i,Team(i,"Team$i","Club$i","ToBeDecided","199$i","$i. division"))
            viewModelScope.launch {
                repository.insertTeam(teams.elementAt(i))
            }
        }
    }
 */