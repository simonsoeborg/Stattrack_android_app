package com.example.stattrack.Data.database.local.logik

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stattrack.Data.database.Entity.PlayerEntity
import com.example.stattrack.Data.database.Entity.PlayerStatsEntity
import kotlinx.coroutines.flow.Flow

interface PlayerStatsDao {

    @Query("SELECT * FROM playerStats WHERE id = :id")
    fun loadById(id: String): Flow<PlayerStatsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: PlayerEntity)

}