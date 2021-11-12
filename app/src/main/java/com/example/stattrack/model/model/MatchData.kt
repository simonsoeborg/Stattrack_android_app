package com.example.stattrack.model.model

import java.util.*

data class MatchData (
    val id : Int,
    val creatorId : String,
    val creatorTeamId : Int,
    val opponent : String,
    val matchDate : Date,
    val creatorTeamGoals : Int,
    val opponentGoals : Int
    )
