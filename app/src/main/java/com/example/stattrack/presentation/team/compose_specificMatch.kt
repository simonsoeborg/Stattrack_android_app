package com.example.stattrack.presentation.team


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.EventData
import com.example.stattrack.model.model.MatchData
import com.example.stattrack.presentation.match.LogComponent
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite

@Composable
fun SpecificMatchScreen(navController: NavHostController, specificMatchViewModel: SpecificMatchViewModel, matchId: Int) {

    specificMatchViewModel.getMatchDataFromMatchId(matchId)
    specificMatchViewModel.getEventDataFromMatchId(matchId)
    val events: State<List<EventData>> = specificMatchViewModel.events.collectAsState()
    val matchData: State<MatchData> = specificMatchViewModel.matchData.collectAsState()

    SpecificMatchScreenContent(
        matchData = matchData.value,
        events = events.value,
        navController = navController,
        onMatchDelete = { specificMatchViewModel.deleteMatch(it) }
    )
}


@Composable
fun SpecificMatchScreenContent(
    matchData: MatchData,
    events: List<EventData>,
    navController: NavHostController,
    onMatchDelete: (matchId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
    ) {


        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 25.dp)
            ) {
                Text(text = matchData.creatorId, color = PrimaryBlue, fontSize = 26.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(end = 15.dp)
            ) {
                Text(
                    text = matchData.creatorTeamGoals.toString(),
                    color = PrimaryBlue,
                    fontSize = 26.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "MOD", color = Color.Gray, fontSize = 18.sp, fontStyle = Italic)
        }


        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 25.dp)
            ) {
                Text(text = matchData.opponent, color = PrimaryBlue, fontSize = 26.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(end = 15.dp)
            ) {
                Text(
                    text = matchData.opponentGoals.toString(),
                    color = PrimaryBlue,
                    fontSize = 26.sp
                )
            }
        }

        Row{
            LogComponent(events = events)
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
            RemoveMatchButton(
                navController = navController,
                matchData = matchData,
                onMatchDelete = { onMatchDelete(it) }
            )
        }

    }
}

















    @Composable
    fun RemoveMatchButton(
        navController: NavHostController,
        matchData: MatchData,
        onMatchDelete: (matchId: Int) -> Unit
    ) {
        var showDialog by remember { mutableStateOf(false) }
        Button(

            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                showDialog = true
            },

            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(0.4f)


        ) {
            Text(text = "Slet Kamp", color = PrimaryWhite)
        }
        if (showDialog) {
            AlertDialog(
                backgroundColor = PrimaryWhite,
                onDismissRequest = { showDialog = false },
                title = { Text("Slet kamp?", color = PrimaryBlue, fontSize = 30.sp) },
                text = {
                    Text(
                        "Er du sikker på at du vil slette ${matchData.creatorId} mod ${matchData.opponent} ?",
                        color = PrimaryBlue
                    )
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        onClick = {
                            onMatchDelete(matchData.id)
                            navController.navigateUp()
                        }) {
                        Text(text = "Slet", color = PrimaryWhite)
                    }
                },
                dismissButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = { showDialog = false }) {
                        Text("Annuller")
                    }
                })
        }
    }


