package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.EventData
import org.jetbrains.annotations.NotNull

@Entity(tableName = "eventData")
data class EventDataEntity (
    @PrimaryKey val Id : Int,
    val EventType : String,
    val PlayerId : Int,
    val Time : String,
    @NotNull val KampId : Int
)

fun EventDataEntity.toModel(): EventData =
    EventData(Id, EventType, PlayerId, Time, KampId)

fun EventData.toEntity(): EventDataEntity =
    EventDataEntity(Id, EventType, PlayerId, Time, KampId)