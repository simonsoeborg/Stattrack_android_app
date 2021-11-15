package com.example.stattrack.presentation.kamp.data

import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.kamp.KampViewModel


/** [KampViewState] This data class represents the view state for the kamp screen.
 * All of this data should be formatted in a way that the home screen can just
 * take the information and display it
 */
data class KampViewState (
    val teams: List<Team> = emptyList()
) {
    val showLoading: Boolean
        get() = teams.isEmpty()
}