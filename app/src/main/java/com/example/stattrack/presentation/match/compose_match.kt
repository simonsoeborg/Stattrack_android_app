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
import java.text.SimpleDateFormat


@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {
    val teams: State<List<Team>> = matchViewModel.teams.collectAsState()
    val players: State<List<Player>> = matchViewModel.players.collectAsState()
    val events: State<List<EventData>> = matchViewModel.events.collectAsState()
    val currentEventData: State<EventData> = matchViewModel.eventData.collectAsState()
    val currentMatchData: State<MatchData> = matchViewModel.matchData.collectAsState()

    MatchScreenContent(
        teams,
        currentEventData,
        currentMatchData,
        players,
        events,
        navController = navController,
        onUpdateScore = { /* Update score in MatchData/EventData here */ },
        onUpdateMatch = { /* Call viewmodel to update matchData in db here */},
        insertEvent = { /* Insert event into database */ },
        updateEventList = {matchViewModel.getEventsFromMatchId(it)}
    )
}

@Composable
fun MatchScreenContent(
    teams: State<List<Team>>,
    currentEventData: State<EventData>,
    currentMatchData: State<MatchData>,
    players: State<List<Player>>,
    events: State<List<EventData>>,
    navController: NavHostController,
    onUpdateScore: (score: Int) -> Unit,
    onUpdateMatch: (match: MatchData) -> Unit,
    insertEvent: (event: EventData) -> Unit,
    updateEventList: (matchId: Int) -> Unit
)
{

    val currentOnUpdateTeam by rememberUpdatedState(newValue = onUpdateScore(0))
    var teamOneId = remember { mutableStateOf(100)}
    val scoreTeam1 by remember { mutableStateOf(25) }
    val nameTeam2 = remember { mutableStateOf("Hold 2")}
    val scoreTeam2 = remember { mutableStateOf(0)}
    val time = remember { mutableStateOf("00:00")}
    val currentDate = SimpleDateFormat.getDateInstance().toString()
    var currentMatch: MatchData =
        MatchData(
        id = 100,
        creatorId = "null",
        creatorTeamId = teamOneId.value,
        opponent = nameTeam2.value,
        matchDate = currentDate,
        creatorTeamGoals = scoreTeam1,
        opponentGoals = scoreTeam2.value
    )
    val currentEvent  = remember{ mutableStateOf("")}
    val currentEventPlayerId = remember { mutableStateOf(0)}
    var EventData = EventData(1000000,currentEvent.value,currentEventPlayerId.value,"00:00",currentMatch.id)


    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {

            TeamComponent(
                scoreTeam1,
                scoreTeam2.value,
                teams.value,
                onSelectedTeam = {teamOneId.value = it},
                onTeamTwoName = {nameTeam2.value = it },
                onTeamTwoScore = {scoreTeam2.value = it}
            )
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(time.value)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent(
                players.value,
                onEventUpdatePlayerId = { currentEventPlayerId.value = it },
                onEventUpdate = {currentEvent.value = it.title},
                insertEvent = {insertEvent(EventData)},
                updateEventList = {updateEventList(currentMatch.id)}
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