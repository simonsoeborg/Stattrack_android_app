package com.example.stattrack.presentation.match

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import kotlinx.coroutines.flow.*

/**
 * [MatchViewModel] takes as parameter a repository to request data
 * that can be exposed by the [compose_match] flow in order
 * for the view to render the relevant information
 */
class MatchViewModel(private val repository: Repository) : ViewModel() {
    var teamsTest : List<Team> = emptyList()
    var players : List<Player> = emptyList()
    val teams : MutableState<List<Team>> = mutableStateOf(ArrayList())
    val team : MutableState<Team> = mutableStateOf(Team(0,"null","null","null","null","null"))
    /*private val team = MutableLiveData(Team(0,"null","null","null","null","null"))
    val teamId :    LiveData<Int>    = team.map { it.teamId }
    val name :      LiveData<String> = team.map { it.name }
    val clubName :  LiveData<String> = team.map { it.clubName }
    val creatorId : LiveData<String> = team.map { it.creatorId }
    val teamUYear : LiveData<String> = team.map { it.teamUYear }
    val division :  LiveData<String> = team.map { it.division } */



    init {
        /* Fetch data from DB when init so it is ready for use later on
        *  Use viewState.value in Compose */
        fillSQLiteWithDummyData()
        viewModelScope.launch {
           repository.getTeamByName("Jylland")
               .collect { team.value = it }
        }
        //loadAllTeams()
        //loadAllPlayers()
    }

    private fun loadAllTeams() {
        viewModelScope.launch {

        }
    }

    private fun loadAllPlayers() {
        viewModelScope.launch {

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



