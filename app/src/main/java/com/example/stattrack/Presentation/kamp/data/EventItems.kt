package com.example.stattrack.Presentation.kamp.data

sealed class EventItems(var title: String) {
    object Default : EventItems("Vælg hændelse")
    object EventGoal : EventItems("Mål")
    object EventAttempt : EventItems("Skud forsøg") // Skud på mål
    object EventSave : EventItems("Redning") // Målvogter redning
    object EventAssist : EventItems("Assist")
    object EventEjection : EventItems("Udvisning (2 min)") // 2 minutters Udvisning
    object EventYellow : EventItems("Gult Kort") // Gult kort
    object EventRed : EventItems("Rødt Kort") // Rødt Kort
}