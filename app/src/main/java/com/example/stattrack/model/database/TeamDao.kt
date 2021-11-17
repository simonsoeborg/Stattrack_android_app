package com.example.stattrack.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team WHERE teamId = :teamId")
    fun loadById(teamId: Int): Flow<TeamEntity?>

    @Query("SELECT * FROM team WHERE name = :name")
    fun loadByName(name: String): Flow<TeamEntity?>

    @Query("SELECT * FROM team ORDER BY teamId DESC")
    fun loadAll(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: TeamEntity)
}