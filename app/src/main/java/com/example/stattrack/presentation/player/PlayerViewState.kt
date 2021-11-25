package com.example.stattrack.presentation.player

import com.example.stattrack.model.model.*


/** [PlayerViewState] This data class represents the view state for the Player screen. */
data class PlayerViewState(
    val playerStats: List<PlayerStats> = emptyList()
)