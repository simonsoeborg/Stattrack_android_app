package com.example.stattrack.Data.model

data class PlayerStats (
    val Id : Int,
    val SpillerId : Int,
    val Tidspunkt : String,
    val Attempts : Int,
    val Goals : Int,
    val KeeperSaves : Int,
    val Assists : Int,
    val Mins2 : Int,
    val Gulekort : Int,
    val Roedekort : Int,
    val KampId : Int
        )