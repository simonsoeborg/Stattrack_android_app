package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.Player

@Entity(tableName = "spiller")
data class PlayerEntity(
    @PrimaryKey val playerId: Int,
    val name: String,
    val position: String,
    val yob : Int,
    val teamId: Int
)

fun PlayerEntity.toModel(): Player =
    Player(playerId, name, position, yob, teamId)

fun Player.toEntity(): PlayerEntity =
    PlayerEntity(playerId, name, position, yob, teamId)


