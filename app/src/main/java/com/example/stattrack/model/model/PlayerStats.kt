package com.example.stattrack.model.model

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
    val matchId : Int
)

val defaultDummyPlayerStatsData = listOf(
        PlayerStats(0,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(1,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(2,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(3,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(4,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(5,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(6,"13:30",5,3,0,3,2,1,0,1),
        PlayerStats(7,"13:30",5,3,0,3,2,1,0,1)
        )