package com.example.stattrack.Data.database.local.logik

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stattrack.Data.database.Entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

        @Query("SELECT * FROM spiller WHERE playerId = :id")
        fun loadById(id: String): Flow<PlayerEntity?>

        @Query("SELECT * FROM spiller ORDER BY name DESC")
        suspend fun loadAll(): List<PlayerEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(asset: PlayerEntity)
    }