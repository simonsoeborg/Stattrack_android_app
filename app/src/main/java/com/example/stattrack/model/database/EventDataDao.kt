package com.example.stattrack.model.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface EventDataDao {

    @Query("SELECT * FROM eventData WHERE Id = :id")
    fun loadById(id: String): Flow<EventDataEntity?>

    @Query("SELECT * FROM eventData ORDER BY id DESC")
    suspend fun loadAll(): List<EventDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: EventDataEntity)
}