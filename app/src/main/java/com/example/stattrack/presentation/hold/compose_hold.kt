package com.example.stattrack.presentation.hold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultTeamDummyData
import com.example.stattrack.presentation.match.MatchScreen
import com.example.stattrack.presentation.match.MatchViewModel
import com.example.stattrack.presentation.match.MatchViewState
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.services.ServiceLocator

@Composable
fun HoldScreen(teamViewModel: TeamViewModel) {
    val currentState: State<MatchViewState> = teamViewModel.viewState.collectAsState()
    if(currentState.value.showLoading){
        /* Do something while loading */
    }

    else
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                            TeamList(currentState)
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

// Old dummyData

/*@Composable
fun dummydata1() {
    val items = listOf("HØJ U19", "HØJ Elite", "HØJ 2", "HØJ 3")

    items.forEach { item ->
        Text(text = "$item", modifier = Modifier.padding(2.dp), color = PrimaryBlue)
    }
}*/


@Composable
fun TeamList(currentState: State<MatchViewState>) {
    LazyColumn() {

        items(
            items = currentState.value.teams,
            key = { team ->
                // Return a stable + unique key for the item
                team.teamId
            },

        ) {
                    team ->
            Surface(modifier = Modifier.clickable { println(team.name + team.teamId)}){
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
    val previewModel = TeamViewModel(ServiceLocator.repository)
    HoldScreen(previewModel)
}
