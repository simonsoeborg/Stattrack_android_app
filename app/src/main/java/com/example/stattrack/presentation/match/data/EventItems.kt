package com.example.stattrack.presentation.match.data

sealed class EventItems(var title: String, var playerId: Int) {
    object Default : EventItems("Vælg hændelse", 0)
    object EventGoal : EventItems("Mål",0)
    object EventAttempt : EventItems("Skud forsøg",0) // Skud på mål
    object EventSave : EventItems("Redning",0) // Målvogter redning
    object EventAssist : EventItems("Assist",0)
    object EventEjection : EventItems("Udvisning (2 min)",0) // 2 minutters Udvisning
    object EventYellow : EventItems("Gult Kort",0) // Gult kort
    object EventRed : EventItems("Rødt Kort",0) // Rødt Kort
}