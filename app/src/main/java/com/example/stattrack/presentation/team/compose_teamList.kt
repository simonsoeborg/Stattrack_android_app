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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultTeamDummyData
import com.example.stattrack.presentation.navbar.Screen
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun MyTeamsScreen(teamViewModel: TeamViewModel, navController: NavHostController) {
    val currentState: State<TeamViewState> = teamViewModel.viewState.collectAsState()

    MyTeamsScreenContent(
        state = currentState,
        navController = navController,
        onAddTeam = {teamViewModel.insertTeam(team = it)}
    )
}

@Composable
fun MyTeamsScreenContent(
    state: State<TeamViewState>,
    navController: NavHostController,
    onAddTeam: (Team) -> Unit) {
    //var team: Team by remember { mutableStateOf(defaultTeamDummyData[1])}
    var showAddTeamScreen by remember{ mutableStateOf(false)}
    if(showAddTeamScreen){
        AddTeam(navController = navController, onSubmitPressed = {onAddTeam(it)})
        // TODO How do we set showAddTeamScreen to false again?
    }

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
                        OutlinedButton(onClick = { showAddTeamScreen = true },
                            modifier= Modifier
                                .padding(top = 5.dp)
                                .size(40.dp),
                            shape = CircleShape,
                            border= BorderStroke(1.dp, PrimaryBlue),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor =  PrimaryBlue)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "NewTeam Button")
                        }
                        Text(text = "Tilføj Hold", color = PrimaryBlue)
                    }
                }
            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                TeamList(state, navController)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Kamp oversigt", fontSize = 32.sp, color = PrimaryBlue)
            Column(modifier = Modifier.padding(10.dp)) {
                dummydata2()
            }
        }
    }
}

// Inspired by: https://stackoverflow.com/questions/66671902/how-to-create-a-circular-outlined-button-with-jetpack-compose
@Composable
fun NewTeamButton(){
    OutlinedButton(onClick = { /*TODO*/ },
        modifier= Modifier
            .padding(top = 5.dp)
            .size(40.dp),
        shape = CircleShape,
        border= BorderStroke(1.dp, PrimaryBlue),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  PrimaryBlue)
    ) {
        Icon(Icons.Default.Add, contentDescription = "NewTeam Button")
    }
}

@Composable
fun TeamList(state: State<TeamViewState>, navController: NavHostController) {
    LazyColumn() {
        items(
            items = state.value.teams,
            key = { team ->
                // Return a stable + unique key for the item
                team.teamId
            },

        ) {
                    team ->
            // Clickable sender kun test-data pt.
            Surface(modifier = Modifier.clickable {

                // Pass data
                val specificTeam = Team(team.teamId,team.name,team.clubName,team.creatorId,team.teamUYear,team.division) // User is a parcelable data class.

                navController.currentBackStackEntry?.arguments?.putParcelable("specificTeam", specificTeam)
                navController.navigate(Screen.SpecificTeam.route)

               // println(team.name+team.teamId)
            }){
                Text(team.name,modifier = Modifier.padding(2.dp), color = PrimaryBlue)
            }
        }
    }
}




@Composable
fun dummydata2() {
    val items = listOf("HØJ U19 mod FIF U19", "HØJ Elite mod BSV", "HØJ 2 mod Randers", "HØJ 3 mod Rudersdal")

    items.forEach { item ->
        Text(text = "$item", modifier = Modifier.padding(2.dp), color = PrimaryBlue)
    }
}

@Preview(showBackground = true)
@Composable
fun HoldScreenPreview() {
    //val previewModel = TeamViewModel(ServiceLocator.repository)
   // HoldScreen(previewModel, navController)
}
