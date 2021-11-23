package com.example.stattrack.presentation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(var route: String, var icon: ImageVector, var title: String) {
    object Team : NavItem("Hold", Icons.Default.People, "HOLD")
    object Match : NavItem("Kamp", Icons.Default.SportsHandball, "KAMP")
    object SpecifikTeam : NavItem("SpecificTeam/{Team}", Icons.Default.Person, "TheTEAM")
    object Landing : NavItem("Landing", Icons.Default.Person, "Intro")
}
