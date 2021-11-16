package com.example.stattrack.model.database

import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultDummyPlayerData
import com.example.stattrack.model.model.defaultTeamDummyData
import kotlinx.coroutines.flow.*

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
        val dbPlayerEntity = PlayerEntity(
                player.id,
                player.name,
                player.position,
                player.yob,
                player.teamId
        )
        try {
            database.PlayerDao().insert(dbPlayerEntity)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }
    fun insertPlayerStats() {}

    suspend fun insertTeam(team:Team) {
        _states.value = State(isInProgress = true)
        try {
            val dbTeam = team.toEntity()
            database.TeamDao().insert(dbTeam)
            _states.value = State(isInProgress = false)
        } catch (ex: Exception) {
            _states.value = State(isInProgress = false, error = ex.message)
        }
    }

    fun insertMatch() {}
    fun insertEventData() {}


    /* Read */
    fun fetchPlayerByName(name: String): Player {
        val playEntity: PlayerEntity =  database.PlayerDao().loadByName(name)
        return playEntity.toModel()
    }

    fun fetchAllPlayers(): List<Player> {
        return defaultDummyPlayerData
    }

    fun fetchTeamByName(name: String): Flow<Team> =
        database.TeamDao()
            .loadByName(name)
            .map { it?.toModel() ?: Team(0,"","","","","")
    }

    fun fetchAllTeams(): List<Team> {
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