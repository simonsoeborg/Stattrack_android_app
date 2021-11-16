package com.example.stattrack.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team WHERE teamId = :id")
    fun loadById(id: String): Flow<TeamEntity?>

    @Query("SELECT * FROM team WHERE name = :name")
    fun loadByName(name: String): Flow<TeamEntity?>

    @Query("SELECT * FROM team ORDER BY teamId DESC")
    fun loadAll(): LiveData<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamEntity: TeamEntity)
}