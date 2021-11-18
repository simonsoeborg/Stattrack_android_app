package com.example.stattrack.presentation.match

import com.example.stattrack.model.model.*


/** [MatchViewState] This data class represents the view state for the Match screen. */
data class MatchViewState(
    var teams: List<Team> = emptyList(),
    var players: List<Player> = emptyList(),
    var matchData: List<MatchData> = emptyList()
    /* TODO: Do we need to create login  for the trainer? val loggedInUser: Trainer */
) {
    val showLoading: Boolean
        get() = teams.isEmpty() && players.isEmpty()
}