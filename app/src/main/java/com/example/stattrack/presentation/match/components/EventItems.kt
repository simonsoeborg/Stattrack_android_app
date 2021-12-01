package com.example.stattrack.presentation.match.components

sealed class EventItems(var title: String, var playerId: Int, var playerName: String) {
    object Default : EventItems("Vælg hændelse", 0,"null")
    object EventGoal : EventItems("Mål",0,"null")
    object EventAttempt : EventItems("Skud forsøg",0,"null") // Skud på mål
    object EventSave : EventItems("Redning",0,"null") // Målvogter redning
    object EventAssist : EventItems("Assist",0,"null")
    object EventEjection : EventItems("Udvisning (2 min)",0,"null") // 2 minutters Udvisning
    object EventYellow : EventItems("Gult Kort",0,"null") // Gult kort
    object EventRed : EventItems("Rødt Kort",0,"null") // Rødt Kort
}