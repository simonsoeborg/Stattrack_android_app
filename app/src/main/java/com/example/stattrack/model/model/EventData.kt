package com.example.stattrack.model.model

data class EventData (
    val id : Int,
    val eventType : String,
    val playerId : Int,
    val time : String,
    val matchId : Int
    )

enum class EventType {

    MÅL,
    SKUD,
    REDNING,
    ASSIST,
    UDVISNING,
    GULT,
    RØDT
}

val defaultDummyEventData = listOf(
    EventData(1,"Mål",1,"02:50",1),
    EventData(2,"Assist",1,"05:23",1),
    EventData(3,"Rødt kort",1,"09:25",1),
    EventData(4,"Gult kort",1,"11:34",1),
    EventData(5,"Redning",1,"14:38",1),
    EventData(6,"Udvisning",1,"25:59",1),
    EventData(7,"Skud",1,"28:12",2),
    EventData(8,"Skud",1,"28:12",3),
    EventData(9,"Skud",1,"28:12",4),
    EventData(10,"Skud",1,"28:12",5),
    EventData(11,"Skud",1,"28:12",6),
    EventData(12,"Skud",1,"28:12",7)
)