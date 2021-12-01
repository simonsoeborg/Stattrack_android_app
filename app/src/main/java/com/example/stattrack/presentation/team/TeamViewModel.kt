package com.example.stattrack.presentation.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch



/**
 * [TeamViewModel] takes as parameter a repository to request data and insert
 * into private variables that can be exposed as immutable states to the [compose_team] in order
 * for the view to render the relevant information
 */

class TeamViewModel (private val repository: Repository) : ViewModel() {

    private val _teams = MutableStateFlow(defaultTeamDummyData)
    private val _matchData = MutableStateFlow(defaultDummyMatchData)

    val teams:  StateFlow<List<Team>> = _teams
    val matchData: StateFlow<List<MatchData>> = _matchData

    init {
        /* Fetch data from DB when init so it is ready for use later on */
        loadAllTeams()
        loadAllMatchData()
    }


    fun insertTeam(team: Team){
        viewModelScope.launch {
            repository.insertTeam(Team(
                _teams.value.size+1,
                name = team.name,
                clubName = team.clubName,
                creatorId = team.creatorId,
                teamUYear = team.teamUYear,
                division = team.division
            ))
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
                _matchData.value = it
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
