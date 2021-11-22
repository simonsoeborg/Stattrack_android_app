package com.example.stattrack.presentation.match

import com.example.stattrack.model.model.*


/** [MatchViewState] This data class represents the view state for the Match screen. */
data class MatchViewState(
    var teams: List<Team> = emptyList(),
    var players: List<Player> = emptyList(),
    var matchData: List<MatchData> = emptyList(),
    var eventData: List<EventData> = emptyList(),
    var playerStats: List<PlayerStats> = emptyList()

) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty() && matchData.isEmpty() && eventData.isEmpty() && playerStats.isEmpty()

    val currentEventId: Int = 1
    val currentMatchId: Int = 1

}