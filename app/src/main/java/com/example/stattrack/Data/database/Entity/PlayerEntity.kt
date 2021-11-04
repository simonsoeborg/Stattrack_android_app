package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.Spiller

@Entity(tableName = "spiller")
data class PlayerEntity(
    @PrimaryKey val playerId: Int,
    val name: String,
    val position: String,
    val yob : Int
)

fun PlayerEntity.toModel(): Spiller =
    Spiller(playerId, name, position, yob)

fun Spiller.toEntity(): PlayerEntity =
    PlayerEntity(playerId, name, position, yob)


