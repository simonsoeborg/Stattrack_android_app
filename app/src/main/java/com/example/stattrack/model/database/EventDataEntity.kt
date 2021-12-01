package com.example.stattrack.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.model.model.EventData
import org.jetbrains.annotations.NotNull

@Entity(tableName = "eventData")
data class EventDataEntity (
    @PrimaryKey val id : Int,
    val eventType : String,
    val playerId : Int,
    val playerName : String,
    val time : String,
    @NotNull val matchId : Int
)

fun EventDataEntity.toModel(): EventData =
    EventData(id, eventType, playerId, playerName, time, matchId)

fun EventData.toEntity(): EventDataEntity =
    EventDataEntity(id, eventType, playerId, playerName, time, matchId)