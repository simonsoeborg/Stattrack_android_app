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

@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {

    val nameTeamOne = matchViewModel.teams[1].name
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

            TeamComponent(hold1_name = nameTeamOne
                , hold2_name = nameTeam2, hold1_sc = scoreTeam1, hold2_sc = scoreTeam2)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    /*matchViewModel.updateScore(
                        matchId = matchId,
                        teamName = currentState.value.teams[teamOneId].name,
                        newScore = currentState.value.matchData[matchId].creatorTeamGoals+1
                    )*/
                    /* matchViewModel.updateScore() */
                     }) {
                Text("Click me to test")
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