package com.example.stattrack.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.model.model.Player

@Entity(tableName = "player")
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
    PlayerEntity(id, name, position, yob, teamId)


