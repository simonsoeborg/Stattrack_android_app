package com.example.stattrack.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PlayerDao {

        @Query("SELECT * FROM player WHERE playerId = :id")
        fun loadById(id: Int): Flow<PlayerEntity?>

        @Query("SELECT * FROM player WHERE name = :name")
        fun loadByName(name: String): Flow<PlayerEntity?>

        /* Load a whole team in */
        @Query("SELECT * FROM player WHERE teamId = :teamId")
        fun loadByTeamId(teamId: Int): Flow<List<PlayerEntity>>

        /* Load all players in DB */
        @Query("SELECT * FROM player ORDER BY name DESC")
        fun loadAll(): Flow<List<PlayerEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(PlayerEntity: PlayerEntity)

        @Query("DELETE FROM player WHERE playerId = :playerId")
        suspend fun delete(playerId: Int)
    }