package com.example.stattrack.Data.model

data class PlayerStats (
    val id : Int,
    val tidspunkt : String,
    val attempts : Int,
    val goals : Int,
    val keeperSaves : Int,
    val assists : Int,
    val mins2 : Int,
    val gulekort : Int,
    val roedekort : Int,
    val kampId : Int
    )