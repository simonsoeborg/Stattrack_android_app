package com.example.stattrack.presentation.match

import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team


/** [MatchViewState] This data class represents the view state for the Match screen. */
data class MatchViewState(
    val teams: List<Team> = emptyList(),
    val players: List<Player> = emptyList()
    /* TODO: Do we need to create login  for the trainer? val loggedInUser: Trainer */
) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty()
}