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

val defaultTeamDummyData = listOf(
    Team(1,"Jylland","HC Midtjylland","Kasper","1978","1. Division"),
    Team(2,"Grindsted","Grindsted GIF Håndbold","Dennis","1956","Top-top-proffer"),
    Team(3,"Hørsholm","Herre-A","Jørgen","1964","Top-top-proffer"),
    Team(4,"Nørrebronx","Herre-A","Ole","1989","Top-top-proffer"),
    Team(5,"Vejle","Herre-A","Henning","2003","Top-top-proffer"),
    Team(6,"Odense","Herre-A","Christian","1853","Top-top-proffer")
)