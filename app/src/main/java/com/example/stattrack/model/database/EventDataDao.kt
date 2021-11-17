package com.example.stattrack.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDataDao {


    @Query("SELECT * FROM eventData WHERE Id = :id")
    fun loadById(id: String): Flow<List<EventDataEntity>?>

    /* Load events that a player has participated in */
    @Query("SELECT * FROM eventData WHERE playerId = :playerId")
    fun loadByPlayerId(playerId: Int): Flow<List<EventDataEntity>?>

    /* Load event from previously played match */
    @Query("SELECT * FROM eventData WHERE matchId = :matchId")
    fun loadByMatchId(matchId: Int): Flow<List<EventDataEntity>?>

    @Query("SELECT * FROM eventData ORDER BY id DESC")
    fun loadAll(): Flow<List<EventDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(EventData: EventDataEntity)
}