package com.example.stattrack.Services

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.stattrack.Data.model.Team
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }

}

fun fillSQLiteWithDummyData(){
    val repo = ServiceLocator.repository
    val teams: MutableList<Team> = mutableListOf()
    for (i in 1..9) {
        teams.add(i,Team(i,"Team$i","Club$i","ToBeDecided","199$i","$i. division"))
        GlobalScope.launch {
            repo.insertTeam(teams.elementAt(i))
        }
        GlobalScope.launch {
            val team = MutableLiveData(Team(0,"","","","",""))
            repo.fetchTeamByName(teams.elementAt(i).name).collect { team.value = it }
            val teamname: LiveData<String> = team.map { it.name }
            Log.d("App.kt","Succesfully inserted team: ${teamname}")
        }

    }
}