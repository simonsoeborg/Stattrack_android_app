package com.example.stattrack.presentation.team
import com.example.stattrack.model.model.*

/** [TeamViewState] This data class represents the view state for the Match screen. */
data class TeamViewState(
    val teams: List<Team> = emptyList(),
    val players: List<Player> = emptyList(),
    val matchData: List<MatchData> = emptyList(),
    val eventData: List<EventData> = emptyList(),
    val playerStats: List<PlayerStats> = emptyList()

) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty() && matchData.isEmpty() && eventData.isEmpty() && playerStats.isEmpty()
}