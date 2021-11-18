package com.example.stattrack.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.model.model.PlayerStats

@Entity(tableName = "playerStats")
data class PlayerStatsEntity(
    @PrimaryKey val id : Int,
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
    PlayerStats(id, time, attempts,goals, keeperSaves,assists,mins2,yellowCards,redCards,matchId)

fun PlayerStats.toEntity(): PlayerStatsEntity =
    PlayerStatsEntity(id, time, attempts,goals, keeperSaves,assists,mins2,yellowCards,redCards,matchId)