package com.example.stattrack.presentation.team

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.positions
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

    val positionList : List<String> by remember { mutableStateOf(positions) }
    var expanded by remember { mutableStateOf(false) }
    var selectedPosition by remember { mutableStateOf("") }

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
            Text("Tilføj Spiller", color= PrimaryBlue, fontSize = 54.sp)
        }
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
                value = yob,
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
                value = selectedPosition,
                onValueChange = { selectedPosition = it },
                label = { Text("Position") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue),
                trailingIcon = {
                    Icon(icon, "" , Modifier.clickable{expanded = !expanded})
                },
                enabled = false
            )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}) {
            positionList.forEach{
                    position ->
                DropdownMenuItem(onClick = {
                    selectedPosition = position
                    expanded = false
                }) {
                    Text(position, textAlign = TextAlign.Center)
                }
            }
        }
        }


        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = {

                    if (name=="" || yob=="" || selectedPosition==""){
                        Toast.makeText(context, "Alle felter skal udfyldes", Toast.LENGTH_LONG).show()
                    }

                    else {
                        sTeamViewModel.insertPlayer(
                            Player(
                                1000,
                                name,
                                selectedPosition,
                                yob.toInt(),
                                team.teamId
                            )
                        )
                       navController.navigateUp()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue, contentColor = PrimaryWhite)
            ){
                Text(text = "Tilføj Spiller", color = PrimaryWhite)
            }
        }
    }
}