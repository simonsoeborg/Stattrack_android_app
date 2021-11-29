package com.example.stattrack.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerStatsDao {

    @Query("SELECT * FROM playerStats WHERE playerId = :playerId")
    fun loadById(playerId: Int): Flow<List<PlayerStatsEntity>>

    @Query("SELECT * FROM playerStats ORDER BY playerId DESC")
    fun loadAll(): Flow<List<PlayerStatsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(PlayerStatsEntity: PlayerStatsEntity)

    @Query("DELETE FROM playerStats WHERE playerId = :playerId")
    suspend fun deletePlayerStatsForPlayer(playerId: Int)
}