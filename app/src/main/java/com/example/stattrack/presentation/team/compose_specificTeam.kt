package com.example.stattrack.presentation.team

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun SpecificTeamScreen(navController: NavHostController, teamViewModel: SpecificTeamViewModel, team : Team ) {
    teamViewModel.loadAllPlayersFromTeam(team.teamId)
    val myPlayers : State<List<Player>> = teamViewModel.players.collectAsState()


    Column() {
        SpecificTeamScreenContent(team = team,
        onUpdatePlayers = {teamViewModel.loadAllPlayersFromTeam(team.teamId)},
        myPlayers.value)

        //Button(onClick = { navController.navigate(NavItem.Team.route)}) {
        }
    }


@Composable
fun SpecificTeamScreenContent(team : Team, onUpdatePlayers: () -> Unit, players: List<Player>) {

    Column() {

        val currentOnUpdateTeam by rememberUpdatedState(newValue = onUpdatePlayers)

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {


                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(){
                        Text(text = team.name, fontSize = 32.sp, color = PrimaryBlue)
                    }

                    Box(Modifier.align(Alignment.CenterEnd)) {
                        Row() {
                            Column(modifier = Modifier.padding(end = 5.dp)) {
                                Text(text = "Division", color = Color.Gray, fontStyle = Italic)
                                Text(text = "Årgang", color = Color.Gray, fontStyle = Italic)
                            }
                            Column(modifier = Modifier.padding(start = 15.dp)) {
                                Text(text = team.division, color = PrimaryBlue)
                                Text(text = team.teamUYear, color = PrimaryBlue)
                            }
                        }
                    }
                }

                Text(text = "Spillerliste", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)

                LazyColumn(contentPadding = PaddingValues(5.dp)) {
                    items(players) { player ->
                        PlayerListItem(player = player)
                    }
                }
            }
        }
    }
}



@Composable
fun PlayerListItem(player: Player){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = player.name, color = PrimaryBlue, modifier = Modifier.padding(start = 10.dp))
        Text(text = player.position, color = PrimaryBlue, modifier = Modifier.padding(start = 10.dp))
    }
}




@Preview(showBackground = true)
@Composable
    fun DefaultPreview() {
        var team = Team(22,"Simon Fridolf","Horsens Boldklub","02020","1996","3.Division")

      // SpecificTeamScreenContent(team = team)
    }

