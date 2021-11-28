package com.example.stattrack.presentation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(var route: String, var icon: ImageVector, var title: String) {
    object Team : Screen("Team", Icons.Default.People, "Hold")
    object CreateTeam : Screen("CreateTeam",Icons.Default.People, "CreateTeam")
    object Match : Screen("Match", Icons.Default.SportsHandball, "Kamp")
    object Player : Screen("PlayerClass", Icons.Default.Person, "Spiller")
    object SpecificTeam : Screen("SpecificTeam", Icons.Default.Person, "TheTEAM")
    object Landing : Screen("Landing", Icons.Default.Person, "Intro")
}
