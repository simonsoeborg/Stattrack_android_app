package com.example.stattrack.presentation.team

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.navbar.Screen
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.presentation.ui.theme.Typography

@Composable
fun AddPlayer(
    sTeamViewModel: SpecificTeamViewModel,
    navController: NavHostController,
    team: Team
) {

    var name by remember { mutableStateOf("") }
    var yob by remember { mutableStateOf("1985") }
    var position by remember { mutableStateOf("")}

    var expanded by remember { mutableStateOf(false) }
    var selectedDivision by remember { mutableStateOf("") }

    val context = LocalContext.current


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
        .background(
            color = PrimaryWhite
        )
    ){
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Navn") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue
                )
            )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = yob.toString(),
                onValueChange = { yob = it  },
                label = { Text("Fødselsår") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue
                )
            )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = position,
                onValueChange = { position = it },
                label = { Text("Position") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue
                )
            )
        }


        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = {

                    if (name=="" || yob=="" || position==""){
                        Toast.makeText(context, "Alle felter skal udfyldes", Toast.LENGTH_LONG).show()
                    }

                    else {
                        sTeamViewModel.insertPlayer(
                            Player(
                                1000,
                                name,
                                position,
                                yob.toInt(),
                                team.teamId
                            )
                        )
                        // Pass data
                        val team = Team(team.teamId,team.name,team.clubName,team.creatorId,team.teamUYear,team.division) // User is a parcelable data class.

                        navController.currentBackStackEntry?.arguments?.putParcelable("specificTeam", team)
                        navController.navigate(Screen.SpecificTeam.route){
                            navController.popBackStack(Screen.AddPlayer.route, false, false)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue, contentColor = PrimaryWhite)
            ){
                Text(text = "Tilføj Spiller", color = PrimaryWhite)
            }
        }
    }
}