package com.example.stattrack.model.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface MatchDataDao {

    @Query("SELECT * FROM matchData WHERE Id = :id")
    fun loadById(id: String): Flow<MatchDataEntity?>

    @Query("SELECT * FROM matchData ORDER BY id DESC")
    suspend fun loadAll(): List<MatchDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: MatchDataEntity)
}