package com.example.stattrack.presentation.kamp

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.kamp.data.KampViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KampViewModel(private val repository: Repository) : ViewModel() {

     // Compose or activity has no way of seeing this.
    private val _viewState = MutableStateFlow(KampViewState())
    // Read-only for the view-layer
    val viewState: StateFlow<KampViewState> = _viewState

    init {
        loadAllTeams()
    }
    // TODO - update repo with data from sqllite and implement online db to refresh sqlite
    fun loadAllTeams() {
        viewModelScope.launch {
            val teams = repository.fetchAllTeams()
            _viewState.value = _viewState.value.copy(
                teams = teams
            )
        }
    }

    fun fillSQLiteWithDummyData(){
        val teams: MutableList<Team> = mutableListOf()
        for (i in 1..9) {
            teams.add(i,Team(i,"Team$i","Club$i","ToBeDecided","199$i","$i. division"))
            viewModelScope.launch {
                repository.insertTeam(teams.elementAt(i))
            }
        }
    }
}

