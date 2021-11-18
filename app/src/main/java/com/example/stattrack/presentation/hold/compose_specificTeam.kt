package com.example.stattrack.presentation.hold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SpecificTeamScreen(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "This is the specific team page")
    }
}