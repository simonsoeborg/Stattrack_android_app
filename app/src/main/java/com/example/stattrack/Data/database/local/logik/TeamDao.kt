package com.example.stattrack.Data.database.local.logik

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stattrack.Data.database.Entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team WHERE teamId = :id")
    fun loadById(id: String): Flow<TeamEntity?>

    @Query("SELECT * FROM team ORDER BY name DESC")
    suspend fun loadAll(): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: TeamEntity)
}