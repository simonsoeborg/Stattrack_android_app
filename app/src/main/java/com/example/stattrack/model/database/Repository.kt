package com.example.stattrack.model.database

import com.example.stattrack.model.model.*
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

    /* Create and Update methods  */
    suspend fun insertEventData(eventData: EventData) {
        _states.value = State(isInProgress = true)
        val dbEventData = eventData.toEntity()
        try {
            database.EventDataDao().insert(dbEventData)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }

    suspend fun insertMatchData(matchData: MatchData) {
        _states.value = State(isInProgress = true)
        val dbMatchData = matchData.toEntity()
        try {
            database.MatchDataDao().insert(dbMatchData)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }

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

    suspend fun insertPlayerStats(playerStats: PlayerStats) {
        _states.value = State(isInProgress = true)
        val dbPlayerStats = playerStats.toEntity()
        try {
            database.PlayerStatsDao().insert(dbPlayerStats)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }

    suspend fun insertTeam(team: Team) {
        _states.value = State(isInProgress = true)
        val dbTeam = team.toEntity()
        try{
            database.TeamDao().insert(dbTeam)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }








    /* Read */
    fun getPlayerByName(name: String): Flow<Player> =
        database.PlayerDao()
            .loadByName(name)
            .map { it?.toModel() ?: Player(0,"null","null",0,0) }

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
    fun getDummyTeams(): List<Team> {
        return defaultTeamDummyData
    }
    fun getDummyPlayers(): List<Player> {
        return defaultDummyPlayerData
    }
}