package com.example.stattrack.presentation.match

import com.example.stattrack.model.model.*

data class MatchViewState(
    val teams: List<Team> = emptyList(),
    val players: List<Player> = emptyList(),
    val matchData: List<MatchData> = emptyList(),
    val eventData: List<EventData> = emptyList(),
    val playerStats: List<PlayerStats> = emptyList()
)