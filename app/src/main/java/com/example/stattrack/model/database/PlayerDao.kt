package com.example.stattrack.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stattrack.model.model.Player

@Dao
interface PlayerDao {

        @Query("SELECT * FROM player WHERE playerId = :id")
        fun loadById(id: Int): PlayerEntity

        @Query("SELECT * FROM player WHERE name = :name")
        fun loadByName(name: String): PlayerEntity

        @Query("SELECT * FROM player ORDER BY name DESC")
        suspend fun loadAll(): List<PlayerEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(PlayerEntity: PlayerEntity)
    }