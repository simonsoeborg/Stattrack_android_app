package com.example.stattrack.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerStatsDao {

    @Query("SELECT * FROM playerStats WHERE id = :playerId")
    fun loadById(playerId: Int): Flow<PlayerStatsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(PlayerStatsEntity: PlayerStatsEntity)
}