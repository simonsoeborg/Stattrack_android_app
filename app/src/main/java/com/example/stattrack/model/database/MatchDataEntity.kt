package com.example.stattrack.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.model.model.MatchData

@Entity(tableName = "matchData")
data class MatchDataEntity (
    @PrimaryKey val id : Int,
    val creatorId : String,
    val creatorTeamId : Int,
    val opponent : String,
    val matchDate : String,
    val creatorTeamGoals : Int,
    val opponentGoals : Int
)

fun MatchDataEntity.toModel(): MatchData =
    MatchData(id, creatorId, creatorTeamId, opponent, matchDate, creatorTeamGoals, opponentGoals)

fun MatchData.toEntity(): MatchDataEntity =
    MatchDataEntity(id, creatorId, creatorTeamId, opponent, matchDate, creatorTeamGoals, opponentGoals)