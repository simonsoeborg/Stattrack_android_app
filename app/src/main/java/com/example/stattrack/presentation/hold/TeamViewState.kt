package com.example.stattrack.presentation.hold
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team

/** [TeamViewState] This data class represents the view state for the Match screen. */
data class TeamViewState (
    val teams: List<Team> = emptyList(),
    val players: List<Player> = emptyList()
) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty()
}