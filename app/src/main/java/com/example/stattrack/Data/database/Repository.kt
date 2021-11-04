package com.example.stattrack.Data.database

import com.example.stattrack.Data.database.Entity.PlayerEntity
import com.example.stattrack.Data.database.local.AppDatabase
import com.example.stattrack.Data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
    fun insertTeam() {}
    fun insertMatch() {}
    fun insertEventData() {}


    /* Read */
    fun loadPlayer() {}

    /* Update */

    /* Delete */
}