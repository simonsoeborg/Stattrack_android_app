package com.example.stattrack.Presentation.kamp.data

sealed class LogItems(var entry: String) {
    object LogGoal : LogItems("10:38 | Anders Andersen | Scorede mål | Stilling: 5 - 7")
    object LogSave : LogItems("07:03 | Bent Bentsen | Redning | Stilling: 7 - 3")
    object LogAssist : LogItems("10:38 | Gunnar Gertsen | Assisteret til mål | Stilling: 5 - 7")
    object LogAttempt : LogItems("08:18 | Jan Jensen | Skød på mål | Stilling: 10 - 7")
    object LogEjection : LogItems("27:17 | Michael Madsen | Udvisning | Stilling: 40 - 48")
    object LogYellow : LogItems("34:23 | Dennis Danni | Gult kort | Stilling: 30 - 32")
    object LogRed : LogItems("59:15 | Kenneth Knudsen | Rødt kort og Udvisning | Stilling: 50 - 50")
}