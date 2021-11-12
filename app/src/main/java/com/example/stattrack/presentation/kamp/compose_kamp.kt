package com.example.stattrack.presentation.kamp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stattrack.presentation.kamp.components.TeamComponent
import com.example.stattrack.services.ServiceLocator
import com.example.stattrack.services.ServiceLocator.kampViewModel

@Composable
fun KampScreen() {
    val Team = KampViewModel.
    val hold1_navn by remember { mutableStateOf("Indtast Hold") }
    val hold1_score by remember { mutableStateOf("0")}
    val hold2_navn by remember { mutableStateOf( "Indtast Hold") }
    val hold2_score by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}


    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            TeamComponent(hold1_name = hold1_navn, hold2_name = hold2_navn, hold1_sc = hold1_score, hold2_sc = hold2_score)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(time)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent()
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun KampScreenPreview() {
    KampScreen()
}
