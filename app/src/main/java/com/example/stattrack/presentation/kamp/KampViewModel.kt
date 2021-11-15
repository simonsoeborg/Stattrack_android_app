package com.example.stattrack.presentation.kamp


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.Team
import com.example.stattrack.services.ServiceLocator.kampViewModel


class KampViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    // Fragment, compose or activity has no way of seeing this.
    val _teams: MutableState<List<Team>> = mutableStateOf(listOf())
    // Read-only for the view-layer
    /* val teams: LiveData<List<Team>> get() = _teams */
    val team = MutableLiveData(Team(0,"","","","",""))
    val id: LiveData<Int> = team.map{it.teamId}
    val name : LiveData<String> = team.map { it.name }
    val clubName : LiveData<String> = team.map { it.clubName }
    val creatorId : LiveData<String> = team.map { it.creatorId }
    val teamUYear : LiveData<String> = team.map { it.teamUYear }
    val division : LiveData<String> = team.map { it.division }

    init {
        fillSQLiteWithDummyData()
        viewModelScope.launch {
        repository.fetchTeamByName("Team1")
            .collect {team.value = it}
            Log.d("StattrackDebug", "On fetchTeamByName: ${team.value?.name}")
        }
        //loadAvailableTeams()
        //loadAvailablePlayers()
    }
    // TODO
    private fun loadAvailableTeams() {
        viewModelScope.launch {
        }
    }

    private fun fillSQLiteWithDummyData(){
        val teams: MutableList<Team> = mutableListOf()
        for (i in 1..9) {
            teams.add(i,Team(i,"Team$i","Club$i","ToBeDecided","199$i","$i. division"))
            viewModelScope.launch {
                repository.insertTeam(teams.elementAt(i))
            }
        }
    }

    /*
    private fun retrieveDummyDataFromSQLite(teamName :String) {
            val team = MutableLiveData(Team(0,"","","","",""))
            val teamname: LiveData<String> = team.map { it.name }
        GlobalScope.launch {
            repository.fetchTeamByName(teamName)
                .collect{ team.value = it }
        }
            Log.d("App.kt","Succesfully inserted team: ${teamname}")
    } */
}
