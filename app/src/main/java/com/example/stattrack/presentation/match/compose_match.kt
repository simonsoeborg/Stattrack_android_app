package com.example.stattrack.presentation.match

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.services.ServiceLocator

@Composable
fun MatchScreen(matchViewModel: MatchViewModel, navController: NavHostController) {

    val nameTeam1  = matchViewModel.team.value.name
    val scoreTeam1 by remember { mutableStateOf("0")}
    val nameTeam2 by remember { mutableStateOf("Indtast hold 2")}
    val scoreTeam2 by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}


    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            TeamComponent(hold1_name = nameTeam1, hold2_name = nameTeam2, hold1_sc = scoreTeam1, hold2_sc = scoreTeam2)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(time)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent()
        }
        OutlinedButton(onClick = { Log.d("ButtonClick","We pressing"); matchViewModel.fillSQLiteWithDummyData() }) {
            Icon(Icons.Default.PlayCircle, contentDescription = "Play", tint = PrimaryBlue)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MatchScreenPreview() {
    val previewModel = MatchViewModel(ServiceLocator.repository)
    previewModel.loadDummyData()
    //MatchScreen(previewModel, navController)
}
