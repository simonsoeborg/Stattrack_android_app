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
    EventData(1,"Træningskamp",1,"23:50",1),
    EventData(2,"Landskamp",1,"23:50",2),
    EventData(3,"Landskamp",1,"23:50",3),
    EventData(4,"Landskamp",1,"23:50",4),
    EventData(5,"Landskamp",1,"23:50",5),
    EventData(6,"Landskamp",1,"23:50",6),
    EventData(7,"Landskamp",1,"23:50",7)
)