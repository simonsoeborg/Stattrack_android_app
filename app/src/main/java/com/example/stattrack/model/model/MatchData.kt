package com.example.stattrack.model.model

data class MatchData(
    val id: Int,
    val creatorId: String,
    val creatorTeamId: Int,
    val opponent: String,
    val matchDate: String,
    var creatorTeamGoals: Int,
    val opponentGoals: Int
    )

val defaultDummyMatchData = listOf(
    MatchData(1,"HC Midtjylland",1,"Nørrebronx Dribblers","28-11-2021",17,35),
    MatchData(2,"DTU Handball",3,"Bornholm United","29-11-2021",39,42),
    MatchData(3,"Shortcut Athletics",4,"Elitesport Vendsyssel","30-11-2021",23,59),
    MatchData(4,"Ajax København",2,"Bornholm United","01-12-2021",30,13)
    //MatchData(5,"Grindsted GIF Håndbold",4,"Tønder Herrebåndbold","27-11-2021",15,35),
    //MatchData(6,"HC Midtjylland",0,"Hold 2","05-11-2021",0,0),
    //MatchData(7,"Ajax København",1,"Grindsted GIF Håndbold","20-11-2021",4,16),
    //MatchData(8,"Tønder Herrebåndbold",2,"Nørrebronx Driblers","19-11-2021",23,19),
    //MatchData(9,"Elitesport Vendsyssel",3,"DTU Handball","26-11-2021",41,25),
)
