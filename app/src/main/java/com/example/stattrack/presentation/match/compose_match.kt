package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.match.data.EventItems


@Composable
fun MatchScreen(matchViewModel: MatchViewModel) {
    val teams: State<List<Team>> = matchViewModel.teams.collectAsState()
    val players: State<List<Player>> = matchViewModel.players.collectAsState()
    val events: State<List<EventData>> = matchViewModel.events.collectAsState()
    val currentMatchData: State<MatchData> = matchViewModel.matchData.collectAsState()
    val timeElapsed: State<String> = matchViewModel.timer.collectAsState()
    val isRunning: State<Boolean> = matchViewModel.isRunning.collectAsState()

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
        onPlayPressed = { matchViewModel.onPlayPressed() },
        onStopPressed = { matchViewModel.onStopPressed() }
    )
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
                onTeamTwoScore = { }
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
            LogComponent(events.value,players.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenPreview() {
    //MatchContent(matchViewState)
    
}