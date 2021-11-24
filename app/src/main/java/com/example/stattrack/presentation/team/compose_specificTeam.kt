package com.example.stattrack.presentation.team

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.stattrack.presentation.navbar.Screen
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun SpecificTeamScreen(navController: NavHostController, teamViewModel: SpecificTeamViewModel, team : Team ) {
    teamViewModel.loadAllPlayersFromTeam(team.teamId)
    val specificTeamPlayers : State<List<Player>> = teamViewModel.players.collectAsState()


    Column() {
        SpecificTeamScreenContent(team = team,
        onAddPlayer = {/*teamViewModel.insertPlayer(player = player)*/},
        specificTeamPlayers.value,
        navController = navController
        )}
    }


@Composable
fun SpecificTeamScreenContent(
    team : Team,
    onAddPlayer: (player: Player) -> Unit,
    specificTeamPlayers: List<Player>,
    navController: NavHostController
) {

    Column() {
        val placeholderPlayer = Player(1000,"placeholder", "placeholder",2021,1000)
        val currentOnAddPlayer by rememberUpdatedState(newValue = onAddPlayer(placeholderPlayer))

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {

                    Box(){ // Headline for team name
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

                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(Modifier.align(Alignment.CenterStart)) {
                        Text(text = "Spillerliste", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                    }

                    Box(Modifier.align(Alignment.CenterEnd)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NewPlayerButton(navController = navController, team = team)
                            Text(text = "Tilføj Spiller", color = PrimaryBlue)
                        }
                    }
                }

                LazyColumn(contentPadding = PaddingValues(5.dp)) {
                    items(specificTeamPlayers) { player ->
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

// Inspired by: https://stackoverflow.com/questions/66671902/how-to-create-a-circular-outlined-button-with-jetpack-compose
@Composable
fun NewPlayerButton(navController: NavHostController, team: Team){
    OutlinedButton(onClick = { navController.navigate(Screen.Landing.route) },
        modifier= Modifier
            .padding(top = 5.dp)
            .size(40.dp),
        shape = CircleShape,
        border= BorderStroke(1.dp, PrimaryBlue),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  PrimaryBlue)
    ) {
        Icon(Icons.Default.Add, contentDescription = "NewPlayer Button")
    }
}


@Preview(showBackground = true)
@Composable
    fun DefaultPreview() {
        var team = Team(22,"Simon Fridolf","Horsens Boldklub","02020","1996","3.Division")

      // SpecificTeamScreenContent(team = team)
    }

