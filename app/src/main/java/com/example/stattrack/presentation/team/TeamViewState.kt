package com.example.stattrack.presentation.team
import com.example.stattrack.model.model.*

/** [TeamViewState] This data class represents the view state for the Match screen. */
data class TeamViewState(
    var teams: List<Team> = emptyList(),
    var players: List<Player> = emptyList(),
    var matchData: List<MatchData> = emptyList(),
    var eventData: List<EventData> = emptyList(),
    var playerStats: List<PlayerStats> = emptyList()

) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty() && matchData.isEmpty() && eventData.isEmpty() && playerStats.isEmpty()
}