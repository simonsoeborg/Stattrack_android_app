package com.example.stattrack.model.model

data class MatchData(
    val id: Int,
    val creatorId: String,
    val creatorTeamId: Int,
    val opponent: String,
    val matchDate: String,
    val creatorTeamGoals: Int,
    val opponentGoals: Int
    )
