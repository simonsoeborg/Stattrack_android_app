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

    private val _teams = MutableStateFlow(repository.getAllTeams())
    val teams = _teams.asStateFlow()


    init {
        loadAllTeams()
    }

    fun testDataManipulationFromCompose(){
        val testHold = Team(0,"Virker det?"," ","", " "," ")
        val testList = listOf(testHold)
        val matchViewStateTest = MatchViewState(
        testList,
        defaultDummyPlayerData,
        defaultDummyMatchData
        )
        //_hotMatchState.value = matchViewStateTest
    }

    private fun loadAllTeams() {
        viewModelScope.launch {
        }
    }

    private fun loadAllPlayers() {
        viewModelScope.launch {
        }
    }
}



