package com.example.stattrack.model.database

import androidx.room.Entity
import com.example.stattrack.model.model.PlayerStats

@Entity(tableName = "playerStats",primaryKeys = ["playerId", "matchId"])
data class PlayerStatsEntity(
    val playerId : Int,
    val time : String,
    val attempts : Int,
    val goals : Int,
    val keeperSaves : Int,
    val assists : Int,
    val mins2 : Int,
    val yellowCards : Int,
    val redCards : Int,
    val matchId : Int
)

fun PlayerStatsEntity.toModel(): PlayerStats =
    PlayerStats(playerId, time, attempts,goals, keeperSaves,assists,mins2,yellowCards,redCards,matchId)

fun PlayerStats.toEntity(): PlayerStatsEntity =
    PlayerStatsEntity(playerId, time, attempts,goals, keeperSaves,assists,mins2,yellowCards,redCards,matchId)