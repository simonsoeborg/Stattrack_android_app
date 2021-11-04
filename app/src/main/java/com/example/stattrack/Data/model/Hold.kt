package com.example.stattrack.Data.model

data class Hold(
    val teamId : Int,
    val name : String,
    val clubName : String,
    // Skal nedenstående stadig bruges?
    val creatorId : String,
    val teamUYear : String,
    val division : String
)