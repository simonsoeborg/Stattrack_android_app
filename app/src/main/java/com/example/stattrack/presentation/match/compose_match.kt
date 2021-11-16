package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.services.ServiceLocator

@Composable
fun MatchScreen(matchViewModel: MatchViewModel) {
    val currentState: State<MatchViewState> = matchViewModel.viewState.collectAsState()
    if(currentState.value.showLoading){
        /* Do something while loading */
    }

    val team1_name  = currentState.value.teams.first().clubName
    val hold1_score by remember { mutableStateOf("0")}
    val hold2_navn = currentState.value.teams[1].clubName
    val hold2_score by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}


    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            TeamComponent(hold1_name = team1_name, hold2_name = hold2_navn, hold1_sc = hold1_score, hold2_sc = hold2_score)
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
fun MatchScreenPreview() {
    val previewModel = MatchViewModel(ServiceLocator.repository)
    previewModel.loadDummyData()
    MatchScreen(previewModel)
}
