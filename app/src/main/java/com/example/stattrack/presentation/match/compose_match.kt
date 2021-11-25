package com.example.stattrack.presentation.match

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.match.components.TeamComponent


@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {
    val teams: State<List<Team>> = matchViewModel.teams.collectAsState()

    MatchScreenContent(
        teams,
        navController = navController,
        onUpdateScore = { /* Update score in MatchData/EventData here */ },
    )
}

@Composable
fun MatchScreenContent(
    teams: State<List<Team>>,
    navController: NavHostController,
    onUpdateScore: (score: Int) -> Unit) {

    val currentOnUpdateTeam by rememberUpdatedState(newValue = onUpdateScore(0))
    val nameTeamOne by remember { mutableStateOf("Hold 1")}
    val scoreTeam1 by remember { mutableStateOf("25") }
    val nameTeam2 by remember { mutableStateOf("Indtast hold 2")}
    val scoreTeam2 by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}
    //Log.d("MatchContent: ${state.value.teams[0].name}","if null wrong connection to db")

    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {

            TeamComponent(
                hold1_name = teams.value[0].clubName,
                hold2_name = nameTeam2,
                hold1_sc = scoreTeam1,
                hold2_sc = scoreTeam2
            )
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