package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.EventData
import com.example.stattrack.model.model.MatchData
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.match.data.EventItems


@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {
    val teams: State<List<Team>> = matchViewModel.teams.collectAsState()
    val players: State<List<Player>> = matchViewModel.players.collectAsState()
    val events: State<List<EventData>> = matchViewModel.events.collectAsState()
    val currentMatchData: State<MatchData> = matchViewModel.matchData.collectAsState()

    MatchScreenContent(
        teams,
        currentMatchData,
        players,
        events,
        navController = navController,
        onUpdateScore = { /* Update score in MatchData/EventData here */ },
        newEvent = { matchViewModel.insertEvent((it)) },
        setTeamOneName = { matchViewModel.setTeamOneName(it) },
        setTeamTwoName = { matchViewModel.setTeamTwoName(it) },
        onPlayPressed = { matchViewModel.onPlayPressed() },
        onPausePressed = { matchViewModel.onPausePressed() }
    )
}

@Composable
fun MatchScreenContent(
    teams: State<List<Team>>,
    currentMatchData: State<MatchData>,
    players: State<List<Player>>,
    events: State<List<EventData>>,
    navController: NavHostController,
    onUpdateScore: (score: Int) -> Unit,
    newEvent: (event: EventItems) -> Unit,
    setTeamOneName: (teamId: Int) -> Unit,
    setTeamTwoName: (teamTwoName: String) -> Unit,
    onPlayPressed: () -> Unit,
    onPausePressed: () -> Unit
)
{

    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {

            TeamComponent(
                matchData = currentMatchData.value,
                teams = teams.value,
                onSelectedTeamOne = { setTeamOneName(it) },
                onTeamTwoName = { setTeamTwoName(it) },
                onTeamTwoScore = { }

            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(
                "00:30",
                onPlayPressed = {onPlayPressed()},
                onPausePressed = { onPausePressed()}
                )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent(
                players.value,
                newEvent = { newEvent(it) }
            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent(events.value,players.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenPreview() {
    //MatchContent(matchViewState)
    
}