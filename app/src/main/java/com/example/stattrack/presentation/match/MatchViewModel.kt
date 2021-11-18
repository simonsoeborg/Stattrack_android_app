package com.example.stattrack.presentation.match


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    val teams = MutableLiveData(defaultTeamDummyData)





    init {
        teams.postValue(listOf(Team(0,"null","null","null","null","null")))
        loadAllTeams()
    }

    fun testDataManipulationFromCompose(){
        val testHold = Team(0,"Virker det?"," ","", " "," ")
        val testList = listOf(testHold)
        teams.value = testList
    }

    private fun loadAllTeams() {
        viewModelScope.launch() {
            repository.getAllTeams().collect{
                teams.postValue(it)
                Log.d("viewModelScope-test", it[0].name)
            }
        }
    }

    private fun loadAllPlayers() {
        viewModelScope.launch {
        }
    }
}



