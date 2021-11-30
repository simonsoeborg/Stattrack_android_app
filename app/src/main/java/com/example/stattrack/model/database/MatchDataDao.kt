package com.example.stattrack.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDataDao {

    @Query("SELECT * FROM matchData WHERE Id = :id")
    fun loadById(id: Int): Flow<MatchDataEntity?>

    /* Load all matches that a specific CreatorId has created (A trainer e.g.) */
    @Query("SELECT * FROM matchData WHERE creatorId = :creatorId")
    fun loadByCreatorId(creatorId: String): Flow<List<MatchDataEntity>>

    /* Load all matches played on a specific date */
    @Query("SELECT * FROM matchData WHERE matchDate = :matchDate")
    fun loadByMatchDate(matchDate: String): Flow<List<MatchDataEntity>>

    @Query("SELECT * FROM matchData ORDER BY id DESC")
    fun loadAll(): Flow<List<MatchDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(matchData: MatchDataEntity)

    @Query ("DELETE FROM matchData WHERE Id = :matchId")
    fun deleteMatchById(matchId: Int)
}