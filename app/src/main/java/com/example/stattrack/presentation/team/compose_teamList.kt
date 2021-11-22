package com.example.stattrack.presentation.team

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.presentation.match.MatchViewState
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun MyTeamsScreen(teamViewModel: TeamViewModel, navController: NavHostController) {
    val currentState: State<TeamViewState> = teamViewModel.viewState.collectAsState()
    if (currentState.value.showLoading) {
        /* Do something while loading */
    } else MyTeamsScreenContent(state = currentState, navController = navController )
}

@Composable
fun MyTeamsScreenContent(state: State<TeamViewState>, navController: NavHostController){

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
                navController.navigate(NavItem.SpecifikTeam.route)
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
