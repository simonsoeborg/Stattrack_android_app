package com.example.stattrack.Data.database.Entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.PlayerStats

@Entity(tableName = "playerStats")
data class PlayerStatsEntity(
    @PrimaryKey val id : Int,
    val tidspunkt : String,
    val attempts : Int,
    val goals : Int,
    val keeperSaves : Int,
    val assists : Int,
    val mins2 : Int,
    val gulekort : Int,
    val roedekort : Int,
    val kampId : Int
)

fun PlayerStatsEntity.toModel(): PlayerStats =
    PlayerStats(id, tidspunkt, attempts,goals, keeperSaves,assists,mins2,gulekort,roedekort,kampId)

fun PlayerStatsEntity.toEntity(): PlayerStatsEntity =
    PlayerStatsEntity(id, tidspunkt, attempts,goals, keeperSaves,assists,mins2,gulekort,roedekort,kampId)
