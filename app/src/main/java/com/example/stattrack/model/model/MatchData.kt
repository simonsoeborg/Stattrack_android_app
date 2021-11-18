package com.example.stattrack.model.model

data class MatchData(
    val id: Int,
    val creatorId: String,
    val creatorTeamId: Int,
    val opponent: String,
    val matchDate: String,
    val creatorTeamGoals: Int,
    val opponentGoals: Int
    )

val defaultDummyMatchData = listOf(
    MatchData(1,"Dev",1,"Modstander hold","18-11-2021",4,16),
    MatchData(2,"Dev",2,"Modstander hold","18-11-2021",23,19),
    MatchData(3,"Dev",3,"Modstander hold","18-11-2021",41,25),
    MatchData(4,"Dev",4,"Modstander hold","18-11-2021",15,35),
    MatchData(5,"Dev",5,"Modstander hold","18-11-2021",17,35),
    MatchData(6,"Dev",6,"Modstander hold","18-11-2021",39,42),
    MatchData(7,"Dev",7,"Modstander hold","18-11-2021",23,59),
    MatchData(8,"Dev",8,"Modstander hold","18-11-2021",30,13)
)
