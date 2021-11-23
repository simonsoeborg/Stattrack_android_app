package com.example.stattrack.presentation.team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.player.PlayerClass
import com.example.stattrack.presentation.ui.theme.StattrackTheme

@Composable
fun SpecificTeamScreen(navController: NavHostController, teamViewModel: SpecificTeamViewModel, team : Team ) {
    Column(Modifier.fillMaxSize()) {

        val myPlayers : State<List<Player>> = teamViewModel.players.collectAsState()


        SpecificTeamScreenContent(team = team,
        onUpdatePlayers = {teamViewModel.loadAllPlayersFromTeam(team.teamId)})

        Button(onClick = { navController.navigate(NavItem.Hold.route)}) {
        }
    }
}

@Composable
fun SpecificTeamScreenContent(team : Team, onUpdatePlayers: () -> Unit) {
    Column() {
        Text(text = "This is the specific team page")
        Text(text = team.name)
    }
}



@Preview(showBackground = true)
@Composable
    fun DefaultPreview() {
        var team = Team(22,"Simon Fridolf","Horsens Boldklub","02020","1996","3.Division")

      // SpecificTeamScreenContent(team = team)
    }

