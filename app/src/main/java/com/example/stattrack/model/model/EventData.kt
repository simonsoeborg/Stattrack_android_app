package com.example.stattrack.model.model

data class EventData (
    val id : Int,
    val eventType : String,
    val playerId : Int,
    val playerName : String,
    val time : String,
    val matchId : Int
    )

val defaultDummyEventData = listOf(
    EventData(1,"Mål",1,"Albert Antonsen","02:50",1),
    EventData(2,"Assist",1,"Albert Antonsen","05:23",1),
    EventData(3,"Rødt kort",1,"Jens Jørgensen","09:25",1),
    EventData(4,"Gult kort",1,"Dennis Dingo","11:34",1),
    EventData(5,"Redning",1,"Erik Eriksen","14:38",1),
    EventData(6,"Udvisning",1,"Jens Jørgensen","25:59",1),

    EventData(7,"Skud",1,"Jens Jørgensen","28:12",1),
    EventData(8,"Mål",1,"Jens Jørgensen","28:12",1),
    EventData(9,"Redning",1,"Jens Jørgensen","28:12",1),
    EventData(10,"Rødt kort",1,"Jens Jørgensen","28:12",1),
    EventData(11,"Skud",1,"Jens Jørgensen","28:12",1),
    EventData(12,"Mål",1,"Jens Jørgensen","28:12",1),

    EventData(13,"Mål",1,"Jens Jørgensen","02:50",1),
    EventData(14,"Assist",1,"Jens Jørgensen","05:23",1),
    EventData(15,"Rødt kort",1,"Jens Jørgensen","09:25",1),
    EventData(16,"Gult kort",1,"Jens Jørgensen","11:34",1),
    EventData(17,"Redning",1,"Jens Jørgensen","14:38",1),
    EventData(18,"Udvisning",1,"Jens Jørgensen","25:59",1),

    EventData(13,"Mål",1,"Jens Jørgensen","02:50",1),
    EventData(14,"Assist",1,"Jens Jørgensen","05:23",1),
    EventData(15,"Rødt kort",1,"Jens Jørgensen","09:25",1),
    EventData(16,"Gult kort",1,"Jens Jørgensen","11:34",1),
    EventData(17,"Redning",1,"Jens Jørgensen","14:38",1),
    EventData(18,"Udvisning",1,"Jens Jørgensen","25:59",1),

)