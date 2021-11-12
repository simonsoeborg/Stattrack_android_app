package com.example.stattrack.model.model

data class Team(
    val teamId : Int,
    val name : String,
    val clubName : String,
    // Skal nedenstående stadig bruges?
    val creatorId : String,
    val teamUYear : String,
    val division : String
)

/* fun Team.toEntity(): TeamEntity =
    TeamEntity(teamId,name,clubName,creatorId,teamUYear,division) */