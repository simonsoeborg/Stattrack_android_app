package com.example.stattrack.presentation.player

import com.example.stattrack.model.model.*


/** [PlayerViewState] This data class represents the view state for the Player screen. */
data class PlayerViewState(
    var teams: List<Team> = emptyList(),
    var players: List<Player> = emptyList(),
    var matchData: List<MatchData> = emptyList(),
    var eventData: List<EventData> = emptyList(),
    var playerStats: List<PlayerStats> = emptyList()

) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty()
}