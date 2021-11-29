package com.example.stattrack.presentation.match.data

sealed class EventItems(var title: String, var playerId: Int, var playerName: String) {
    object Default : EventItems("Vælg hændelse", 0,"Jens Jørgensen")
    object EventGoal : EventItems("Mål",0,"Jens Jørgensen")
    object EventAttempt : EventItems("Skud forsøg",0,"Jens Jørgensen") // Skud på mål
    object EventSave : EventItems("Redning",0,"Jens Jørgensen") // Målvogter redning
    object EventAssist : EventItems("Assist",0,"Jens Jørgensen")
    object EventEjection : EventItems("Udvisning (2 min)",0,"Jens Jørgensen") // 2 minutters Udvisning
    object EventYellow : EventItems("Gult Kort",0,"Jens Jørgensen") // Gult kort
    object EventRed : EventItems("Rødt Kort",0,"Jens Jørgensen") // Rødt Kort
}