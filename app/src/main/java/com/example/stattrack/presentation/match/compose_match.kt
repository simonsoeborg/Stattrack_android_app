package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.stattrack.presentation.match.components.TeamComponent

@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {
    val currentState: State<MatchViewState> = matchViewModel.viewState.collectAsState()

    if (currentState.value.showLoading){
        /* Do something while loading */
    } else
        MatchScreenContent(matchViewModel = matchViewModel, navController = navController )

}

@Composable
fun MatchScreenContent(matchViewModel: MatchViewModel, navController: NavHostController) {
    val currentState: State<MatchViewState> = matchViewModel.viewState.collectAsState()
    val eventId = currentState.value.currentEventId
    val matchId = currentState.value.currentMatchId
    val teamOneId = 0
    val teamTwoId = 2
    val score: Int by matchViewModel.score.collectAsState()



    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {

            TeamComponent(
                hold1_name = currentState.value.teams[teamOneId].name,
                hold2_name = currentState.value.teams[teamTwoId].name,
                hold1_sc = score.toString(),
                hold2_sc = currentState.value.matchData[matchId].opponentGoals.toString())
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    /*matchViewModel.updateScore(
                        matchId = matchId,
                        teamName = currentState.value.teams[teamOneId].name,
                        newScore = currentState.value.matchData[matchId].creatorTeamGoals+1
                    )*/
                    matchViewModel.updateScore()
                }) {
                Text("Click me to test")
            }
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(
                "15:20")
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

}
