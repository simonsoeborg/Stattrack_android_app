package com.example.stattrack.Presentation.kamp


import android.util.Log
import androidx.lifecycle.*
import com.example.stattrack.Data.database.Repository
import com.example.stattrack.Data.model.Team
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class KampViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _teams = MutableLiveData<List<Team>>(emptyList())
    val teams: LiveData<List<Team>> = _teams

    init {
         loadAvailableTeams()
        // loadAvailablePlayers()
    }
    // TODO
    private fun loadAvailableTeams() {
        viewModelScope.launch {
        fillSQLiteWithDummyData()
        retrieveDummyDataFromSQLite("Team1")
        //_teams.value = repository.fetchAllTeams()
        }
    }

    private fun fillSQLiteWithDummyData(){
        val teams: MutableList<Team> = mutableListOf()
        for (i in 1..9) {
            teams.add(i,Team(i,"Team$i","Club$i","ToBeDecided","199$i","$i. division"))
            GlobalScope.launch {
                repository.insertTeam(teams.elementAt(i))
            }
        }
    }

    private fun retrieveDummyDataFromSQLite(teamName :String) {
            val team = MutableLiveData(Team(0,"","","","",""))
            val teamname: LiveData<String> = team.map { it.name }
        GlobalScope.launch {
            repository.fetchTeamByName(teamName)
                .collect{ team.value = it }
        }
            Log.d("App.kt","Succesfully inserted team: ${teamname}")
    }
}
