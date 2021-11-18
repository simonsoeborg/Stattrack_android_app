package com.example.stattrack.model.database

import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultDummyPlayerData
import com.example.stattrack.model.model.defaultTeamDummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Repository (
    private val database: AppDatabase

){
    data class State(
        val isInProgress: Boolean,
        val error: String? = null
    )

    private val _states = MutableStateFlow(State(isInProgress = false))
    val states = _states.asStateFlow()

    /* Create */
    suspend fun insertPlayer(player: Player) {
        _states.value = State(isInProgress = true)
        val dbPlayerEntity = player.toEntity()
        try {
            database.PlayerDao().insert(dbPlayerEntity)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }

    fun insertTeam(team: Team) {
        _states.value = State(isInProgress = true)
        GlobalScope.launch(Dispatchers.IO){
            val dbTeam = team.toEntity()
            database.TeamDao().insert(dbTeam)
        }
        _states.value = State(isInProgress = false)
    }



    /* Read */
    fun fetchPlayerByName(name: String): Player {
        val playEntity: PlayerEntity =  database.PlayerDao().loadByName(name)
        return playEntity.toModel()
    }

    fun getAllPlayers(): List<Player> {
        return defaultDummyPlayerData
    }

    fun getTeamByName(name: String): Flow<Team> =
        database.TeamDao()
            .loadByName(name)
            .map { it?.toModel() ?: Team(0,"null","null","null","null","null") }


    // OBS OBS - Tidligere retunerede denne en Flow<List<Team>> (Hvilket ikke gave meget mening i det jeg lavede)
    /*
     fun getAllTeams(): Flow<List<Team>>{
        return listOf(defaultTeamDummyData).asFlow()
    }
     */
    fun getAllTeams():List<Team>{
        return defaultTeamDummyData
    }



    /* Update */

    /* Delete */


    /* Dummy functions used for @Preview in Compose */
    fun fetchDummyTeams(): List<Team> {
        return defaultTeamDummyData
    }
    fun fetchDummyPlayers(): List<Player> {
        return defaultDummyPlayerData
    }
}