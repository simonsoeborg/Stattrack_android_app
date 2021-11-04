package com.example.stattrack.Data.model

data class PlayerStats (
    val id : Int,
    val time : String,
    val attempts : Int,
    val goals : Int,
    val keeperSaves : Int,
    val assists : Int,
    val mins2 : Int,
    val yellowCards : Int,
    val redCards : Int,
    val MatchId : Int
    )