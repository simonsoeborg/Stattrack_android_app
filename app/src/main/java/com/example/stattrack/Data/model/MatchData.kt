package com.example.stattrack.Data.model

import java.util.*

data class MatchData (
    val Id : Int,
    val CreatorId : String,
    val CreatorTeamId : Int,
    val Modstander : String,
    val KampDato : Date,
    val CreatorTeamGoals : Int,
    val ModstanderGoals : Int
)
