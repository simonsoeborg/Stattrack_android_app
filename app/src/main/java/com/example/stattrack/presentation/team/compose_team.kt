package com.example.stattrack.presentation.team

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.MatchData
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.navbar.Screen
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun MyTeamsScreen(teamViewModel: TeamViewModel, navController: NavHostController) {
    val teams: State<List<Team>> = teamViewModel.teams.collectAsState()
    val matches: State<List<MatchData>> = teamViewModel.matchData.collectAsState()

    MyTeamsScreenContent(
        teams = teams,
        matches = matches,
        navController = navController
    )
}

@Composable
fun MyTeamsScreenContent(
    teams: State<List<Team>>,
    matches: State<List<MatchData>>,
    navController: NavHostController)
{

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(Modifier.align(Alignment.CenterStart)) {
                        Text(text = "Hold oversigt", fontSize = 32.sp, color = PrimaryBlue)
                    }

                    // Add new team button and functionality
                    Box(Modifier.align(Alignment.CenterEnd)) {
                        Column(horizontalAlignment = CenterHorizontally) {
                            OutlinedButton(
                                onClick = { navController.navigate(Screen.CreateTeam.route) },
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .size(40.dp),
                                shape = CircleShape,
                                border = BorderStroke(1.dp, PrimaryBlue),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryBlue)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "NewTeam Button")
                            }
                            Text(text = "Tilføj Hold", color = PrimaryBlue)
                        }
                    }
                }

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    TeamList(teams, navController)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Kamp oversigt", fontSize = 32.sp, color = PrimaryBlue)
                Column(modifier = Modifier.padding(10.dp)) {
                    MatchList(matches = matches, navController = navController)
                }
            }
        }
    }



@Composable
fun TeamList(teams: State<List<Team>>, navController: NavHostController) {
    LazyColumn() {
        items(
            items = teams.value,
            key = { team ->
                // Return a stable + unique key for the item
                team.teamId
            },

        ) {
                    team ->
            Surface(modifier = Modifier.clickable {

                // Pass data
                val specificTeam = Team(team.teamId,team.name,team.clubName,team.creatorId,team.teamUYear,team.division) // User is a parcelable data class.

                navController.currentBackStackEntry?.arguments?.putParcelable("specificTeam", specificTeam)
                navController.navigate(Screen.SpecificTeam.route)

            }){
                Text(team.name,modifier = Modifier.padding(2.dp), color = PrimaryBlue)
            }
        }
    }
}




@Composable
fun MatchList(matches: State<List<MatchData>>, navController: NavHostController) {
    LazyColumn() {
        items(
            items = matches.value
            ) {
                match ->
            Surface(modifier = Modifier.padding(bottom = 10.dp)
            .clickable {

                // Pass data
                val matchId = match.id
                navController.currentBackStackEntry?.arguments?.putInt("matchId", matchId)
                navController.navigate(Screen.SpecificMatch.route)

            }){
                Text("${match.matchDate} \n${match.creatorId} mod ${match.opponent} | ${match.creatorTeamGoals} : ${match.opponentGoals}",modifier = Modifier.padding(2.dp), color = PrimaryBlue)
            }
        }
    }
}
