package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.team.TeamViewState

@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {
    val state: State<MatchViewState> = matchViewModel.viewState.collectAsState()

    MatchScreenContent(state = state, navController = navController, onUpdateTeam = {matchViewModel.updateTeam()} )

}

@Composable
fun MatchScreenContent(
    state: State<MatchViewState>,
    navController: NavHostController,
    onUpdateTeam: () -> Unit) {

    val currentOnUpdateTeam by rememberUpdatedState(newValue = onUpdateTeam)
    val nameTeam1 by remember { mutableStateOf("Hold 1")}
    val scoreTeam1 by remember { mutableStateOf("25") }
    val nameTeam2 by remember { mutableStateOf("Indtast hold 2")}
    val scoreTeam2 by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}

    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {

            TeamComponent(
                hold1_name = nameTeam1,
                hold2_name = nameTeam2,
                hold1_sc = scoreTeam1,
                hold2_sc = scoreTeam2
            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {currentOnUpdateTeam()}) {
                Text("Click me to update name on team 1 to database")
            }
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
    //MatchContent(matchViewState)
    
}