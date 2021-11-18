package com.example.stattrack.presentation.match

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [MatchViewModel] takes as parameter a repository to request data
 * that can be exposed by the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {
    /* cold-flow way of binding ui to viewmodel
    val matchState: Flow<MatchViewState> = repository.getPlayerByName("asd")
        .combine(repository.getTeamByName("asda")) { player, team ->
            MatchViewState(teams = listOf(team), players = listOf(player))
        } */
    
    /* Hot-flow way of binding UI to ViewModel */
    private val _hotMatchState = MutableStateFlow<MatchViewState?>(null)
    val hotMatchState = _hotMatchState.asStateFlow()


    val mutableFlow = MutableStateFlow(4)


    init {
        /* Fetch data from DB when init so it is ready for use later on
        *  Use viewState.value in Compose */
        fillSQLiteWithDummyData()
        /*viewModelScope.launch {
           repository.getAllTeams()
               .collect { teams.value = it }
        }*/
        loadAllTeams()
        loadAllPlayers()
    }

    private fun loadAllTeams() {
        viewModelScope.launch {

        }
    }

    private fun loadAllPlayers() {
        viewModelScope.launch {
            _hotMatchState.value = MatchViewState()
        }
    }

    fun fillSQLiteWithDummyData(){
        viewModelScope.launch {
            val dummyTeams = repository.getDummyTeams()
            for (team in dummyTeams){
                viewModelScope.launch {
                    repository.insertTeam(team)
                    Log.d("Team", team.name)
                    Log.d("ViewModelScope","Inserting into DB")
                }
            }
        }
    }


    /*
    Only used for making @Preview work in compose-files
     */
    fun loadDummyData() {
        viewModelScope.launch {
            val dummyTeams = repository.getDummyTeams()

            teams.value = dummyTeams

        }
        viewModelScope.launch {
            val dummyPlayers = repository.getDummyPlayers()
            players = dummyPlayers
        }
    }
}



