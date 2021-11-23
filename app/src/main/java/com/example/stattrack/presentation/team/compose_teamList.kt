package com.example.stattrack.presentation.team

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.example.stattrack.R
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 3500

@Composable
fun MyTeamsScreen(teamViewModel: TeamViewModel, navController: NavHostController) {
    val currentState: State<TeamViewState> = teamViewModel.viewState.collectAsState()
    /*var showLandingScreen by remember { mutableStateOf(true) }
    if (showLandingScreen) {
        LandingScreen(onTimeout = { showLandingScreen = false })
    } else {
    }*/

    MyTeamsScreenContent(state = currentState, navController = navController, onUpdateTeam = {teamViewModel.updateTeam()} )
}

@Composable
fun MyTeamsScreenContent(state: State<TeamViewState>, navController: NavHostController, onUpdateTeam: () -> Unit){
    val currentOnUpdateTeam by rememberUpdatedState(newValue = onUpdateTeam)
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = CenterHorizontally)
        ) {
            Column {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "Hold oversigt", fontSize = 32.sp, color = PrimaryBlue)
                        Column(modifier = Modifier.padding(10.dp)) {
                            TeamList(state, navController)
                        }
                    }
                }
                Row() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Button(onClick = {currentOnUpdateTeam()}) {
                            Text("Click me to add team to database")
                        }
                        Text(text = "Kamp oversigt", fontSize = 32.sp, color = PrimaryBlue)
                        Column(modifier = Modifier.padding(10.dp)) {
                            dummydata2()
                        }
                    }
                }
            }
        }
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
                println(team.name+team.teamId)
                navController.navigate(NavItem.SpecificTeam.route)
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
