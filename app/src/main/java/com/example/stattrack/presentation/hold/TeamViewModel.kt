package com.example.stattrack.presentation.hold

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.model.database.Repository
import com.example.stattrack.presentation.match.MatchViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



/**
 * [TeamViewModel] takes as parameter a repository to request data and insert
 * into a [TeamViewModel] that can be exposed by the [TeamViewModel] flow in order
 * for the view to render the relevant information
 */

class TeamViewModel (private val repository: Repository) : ViewModel(){

        // View-layer has no way of seeing this.
        private val _viewState = MutableStateFlow(TeamViewState())

        // Read-only for the view-layer
        val viewState: StateFlow<TeamViewState> = _viewState

        init {
            /* Fetch data from DB when init so it is ready for use later on
            *  Use viewState.value in Compose */
            loadAllTeams()
        }

    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                _viewState.value.teams=it
                Log.d("viewModelScope-test", it[0].name)
            }
        }
    }
}

