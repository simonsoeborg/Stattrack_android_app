package com.example.stattrack.Data.database.local.logik

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stattrack.Data.database.Entity.EventDataEntity
import com.example.stattrack.Data.database.Entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface EventDataDao {

    @Query("SELECT * FROM eventData WHERE Id = :id")
    fun loadById(id: String): Flow<EventDataEntity?>

    @Query("SELECT * FROM eventData ORDER BY id DESC")
    suspend fun loadAll(): List<EventDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: EventDataEntity)
}