package com.example.stattrack.Data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stattrack.Data.model.Team

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey val teamId : Int,
    val name: String,
    val clubName : String,
    val creatorId : String,
    val teamUYear : String,
    val division : String
)

fun TeamEntity.toModel(): Team =
    Team(teamId, name, clubName,creatorId, teamUYear,division)

fun Team.toEntity(): TeamEntity =
    TeamEntity(teamId, name, clubName,creatorId, teamUYear,division)
