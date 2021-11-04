package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.EventData
import org.jetbrains.annotations.NotNull

@Entity(tableName = "eventData")
data class EventDataEntity (
    @PrimaryKey val id : Int,
    val eventType : String,
    val playerId : Int,
    val time : String,
    @NotNull val matchId : Int
)

fun EventDataEntity.toModel(): EventData =
    EventData(id, eventType, playerId, time, matchId)

fun EventData.toEntity(): EventDataEntity =
    EventDataEntity(id, eventType, playerId, time, matchId)