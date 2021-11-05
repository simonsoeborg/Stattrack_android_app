package com.example.stattrack.Presentation.kamp

sealed class EventItems(var title: String, var selected: Boolean) {
    object EventGoal : EventItems("Mål", false)
    object EventAttempt : EventItems("Skud forsøg", false) // Skud på mål
    object EventSave : EventItems("Redning", false) // Målvogter redning
    object EventAssist : EventItems("Assist", false)
    object EventEjection : EventItems("Udvisning (2 min)", false) // 2 minutters Udvisning
    object EventYellow : EventItems("Gult Kort", false) // Gult kort
    object EventRed : EventItems("Rødt Kort", false) // Rødt Kort
}