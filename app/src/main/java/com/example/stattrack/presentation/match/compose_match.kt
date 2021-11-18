package com.example.stattrack.presentation.match

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stattrack.presentation.match.components.TeamComponent
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.services.ServiceLocator

@Composable
fun MatchScreen(matchViewModel: MatchViewModel) {

    val nameTeam1  = matchViewModel.team.value.name
    val scoreTeam1 by remember { mutableStateOf("0")}
    val nameTeam2 by remember { mutableStateOf("Indtast hold 2")}
    val scoreTeam2 by remember { mutableStateOf("0")}
    val time by remember { mutableStateOf("00:00")}

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {

        } else -> {

        }
    }


}

@Composable
fun MatchLandscape(matchViewModel: MatchViewModel, time: String) {
    val landscape = true;
    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            // TODO Needs to change to matchViewModel data
            TeamComponent(hold1_name = matchViewModel.team.value.name, hold2_name = "", hold1_sc = "0", hold2_sc = "", landscape)
        }
        Row( modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            StopWatchComponent(time)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent()
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchLandscapePreview() {
    val previewModel = MatchViewModel(ServiceLocator.repository)
    previewModel.loadDummyData()
    MatchLandscape(previewModel, "00:00")
}

@Composable
fun MatchPortrait(matchViewModel: MatchViewModel, time: String) {
    val landscape = false;
    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            // TODO Needs to change to matchViewModel data
            TeamComponent(hold1_name = matchViewModel.team.value.name, hold2_name = "", hold1_sc = "0", hold2_sc = "", landscape)
        }
        Row( modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            StopWatchComponent(time)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent()
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            LogComponent()
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MatchScreenPreview() {
//    val previewModel = MatchViewModel(ServiceLocator.repository)
//    previewModel.loadDummyData()
//    MatchScreen(previewModel)
//}
