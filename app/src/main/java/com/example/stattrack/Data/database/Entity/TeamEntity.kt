package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.Hold

@Entity(tableName = "hold")
data class TeamEntity(
    @PrimaryKey val teamId : Int,
    val name: String,
    val clubName : String,
    val creatorId : String,
    val teamUYear : String,
    val division : String
)

fun TeamEntity.toModel(): Hold =
    Hold(teamId, name, clubName,creatorId, teamUYear,division)

fun TeamEntity.toEntity(): TeamEntity =
    TeamEntity(teamId, name, clubName,creatorId, teamUYear,division)
