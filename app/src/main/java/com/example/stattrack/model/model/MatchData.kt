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
    MatchData(0,"HC Midtjylland",0,"Hold 2","05-11-2021",0,0),
    MatchData(1,"Ajax København",1,"Grindsted GIF Håndbold","20-11-2021",4,16),
    MatchData(2,"Tønder Herrebåndbold",2,"Nørrebronx Driblers","19-11-2021",23,19),
    MatchData(3,"Elitesport Vendsyssel",3,"DTU Handball","26-11-2021",41,25),
    MatchData(4,"Grindsted GIF Håndbold",4,"Tønder Herrebåndbold","27-11-2021",15,35),
    MatchData(5,"Nørrebronx Driblers",5,"Shortcut Athletics","28-11-2021",17,35),
    MatchData(6,"DTU Handball",6,"Bornholm United","29-11-2021",39,42),
    MatchData(7,"Shortcut Athletics",7,"Elitesport Vendsyssel","30-11-2021",23,59),
    MatchData(8,"Bornholm United",8,"Ajax København","01-12-2021",30,13)
)
