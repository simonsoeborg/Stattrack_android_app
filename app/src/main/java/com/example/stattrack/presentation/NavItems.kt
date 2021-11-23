package com.example.stattrack.presentation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(var route: String, var icon: ImageVector, var title: String) {
    object Hold : NavItem("Hold", Icons.Default.People, "HOLD")
    object Kamp : NavItem("Kamp", Icons.Default.SportsHandball, "KAMP")
    object SpecifikTeam : NavItem("SpecificTeam/{Team}", Icons.Default.Person, "TheTEAM")
}
