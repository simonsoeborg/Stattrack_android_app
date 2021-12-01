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

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stattrack.di.ServiceLocator.application

import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.divisions
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.presentation.ui.theme.Typography


@Composable
fun AddTeam(
    teamViewModel: TeamViewModel,
    navController: NavHostController?
) {

    var clubname by remember { mutableStateOf("")}
    var teamName by remember { mutableStateOf("") }
    var creatorName by remember { mutableStateOf("") }
    var teamUYear by remember { mutableStateOf("") }

    val divisionList : List<String> by remember {mutableStateOf(divisions)}
    var expanded by remember { mutableStateOf(false)}
    var selectedDivision by remember { mutableStateOf("")}

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
                value = clubname,
                onValueChange = { clubname = it },
                label = { Text("Klub navn") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue)
            )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = teamName,
                onValueChange = { teamName = it },
                label = { Text("Hold navn") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue)
            )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = creatorName,
                onValueChange = { creatorName = it },
                label = { Text("Træner navn") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue)
                )
        }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = teamUYear,
                onValueChange = { teamUYear = it },
                label = { Text("Årgang") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue)
                )
        }

        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value =selectedDivision,
                onValueChange = { selectedDivision = it },
                label = { Text("Division") },
                textStyle = Typography.body1.copy(
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue),
                trailingIcon = {
                    Icon(icon, "" , Modifier.clickable{expanded = !expanded})
                },
                enabled = false
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}) {
            divisionList.forEach{
                division ->
                DropdownMenuItem(onClick = {
                    selectedDivision = division
                    expanded = false
                }) {
                    Text(division)
                }
            }
        }

        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = {

                    if (teamName=="" || clubname=="" || creatorName=="" || teamUYear==""|| selectedDivision==""){

                        Toast.makeText(application, "Alle parametere skal udfyldes", Toast.LENGTH_LONG).show()
                    }

                    else {
                        teamViewModel.insertTeam(
                            Team(
                                1000,
                                teamName,
                                clubname,
                                creatorName,
                                teamUYear,
                                selectedDivision
                            )
                        )
                        navController?.navigate("Team")
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue, contentColor = PrimaryWhite)
            ){
                Text(text = "Opret hold", color = PrimaryWhite)
            }
        }
    }
}




@Preview
@Composable
fun AddTeamPreview(){
    //ddTeam(navController = null)
}