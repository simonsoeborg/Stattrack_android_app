package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.components.StopWatchComponent
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.match.data.EventItems
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.Typography


@Composable
fun MatchScreen(matchViewModel: MatchViewModel) {
    val teams: State<List<Team>> = matchViewModel.teams.collectAsState()
    val players: State<List<Player>> = matchViewModel.players.collectAsState()
    val events: State<List<EventData>> = matchViewModel.events.collectAsState()
    val currentMatchData: State<MatchData> = matchViewModel.matchData.collectAsState()
    val timeElapsed: State<String> = matchViewModel.timer.collectAsState()
    val isRunning: State<Boolean> = matchViewModel.isRunning.collectAsState()
    val loading = teams.value.isEmpty() || players.value.isEmpty()
    
    if(loading ) {
        /* Show loading */
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            Text(text = "Loading", color= PrimaryBlue, fontSize = 72.sp)
            Text(
                text= "Dette kan være fordi du ikke har tilføjet spillere til dit hold," +
                        "eller ikke oprettet et hold endnu.",
                color = PrimaryBlue,
                fontSize = Typography.body1.fontSize
            )
        }
    } else {
        
    MatchScreenContent(
        teams,
        currentMatchData,
        players,
        events,
        timeElapsed = timeElapsed,
        isRunning = isRunning,
        newEvent = { matchViewModel.insertEvent((it)) },
        setTeamOneName = { matchViewModel.setTeamOneName(it) },
        setTeamTwoName = { matchViewModel.setTeamTwoName(it) },
        onTeamTwoScore = { matchViewModel.setTeamTwoScore(it)},
        onPlayPressed = { matchViewModel.onPlayPressed() },
        onStopPressed = { matchViewModel.onStopPressed() }
    )
    }
}

@Composable
fun MatchScreenContent(
    teams: State<List<Team>>,
    currentMatchData: State<MatchData>,
    players: State<List<Player>>,
    events: State<List<EventData>>,
    timeElapsed: State<String>,
    isRunning: State<Boolean>,
    newEvent: (event: EventItems) -> Unit,
    setTeamOneName: (teamId: Int) -> Unit,
    setTeamTwoName: (teamTwoName: String) -> Unit,
    onTeamTwoScore: (score: Int) -> Unit,
    onPlayPressed: () -> Unit,
    onStopPressed: () -> Unit
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
                onTeamTwoScore = { onTeamTwoScore(it) }
            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {

            StopWatchComponent(
                timeElapsed = timeElapsed,
                isRunning = isRunning,
                onPlayPressed = { onPlayPressed() },
                onStopPressed = { onStopPressed() }
            )


        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent(
                players.value,
                newEvent = { newEvent(it) }
            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent(events.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenPreview() {
    //MatchContent(matchViewState)
    
}