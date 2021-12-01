package com.example.stattrack.presentation.match.components


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.di.ServiceLocator
import com.example.stattrack.model.model.MatchData
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.presentation.ui.theme.Typography


@Composable
fun TeamComponent(
    matchData: MatchData,
    teams:List<Team>,
    onSelectedTeamOne: (teamId: Int) -> Unit,
    onTeamOneName : (String) -> Unit,
    onTeamTwoName: (String) -> Unit,
    onTeamTwoScore: (Int) -> Unit,
    matchStarted: Boolean,
    ){

    val teamTwoName = remember { mutableStateOf("")}

    Row( // Teams Row
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column( // Main Column
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp)
        ) {

            // Team 1 row
            Row(
                modifier = Modifier
                .padding(2.dp),
            )
            {
                    Column( modifier = Modifier
                        .weight(3f)
                        .padding(1.dp)
                    ) {
                        DropdownTeamsList(
                            teams = teams,
                            onSelectedTeam = {
                                onSelectedTeamOne(it) },
                            TeamName = { onTeamOneName(it)},
                            teamName1 = matchData.creatorId,
                            matchStarted
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp)

                    ) {
                        Text(
                            text = matchData.creatorTeamGoals.toString(),
                            color = PrimaryBlue,
                            fontSize = 24.sp,
                        )
                    }

            }
            // Team 2 row
            Row( modifier = Modifier
                .padding(2.dp)
                .background(color = PrimaryWhite)
            )
            {
                Column( modifier = Modifier
                    .weight(3f)
                    .padding(1.dp)
                    .background(color = PrimaryWhite)
                    //.border(2.dp, color = PrimaryBlue) <- doesnt look good find another style
                ) {
                    TextField(
                        value = matchData.opponent,
                        onValueChange = {

                            if (matchStarted)
                            Toast.makeText(ServiceLocator.application, "Modstander navn kan ikke ændres under kamp", Toast.LENGTH_LONG).show()

                            else {
                                teamTwoName.value = it
                                onTeamTwoName(it)
                            }
                        },
                        placeholder = {
                            Text(text = "Vælg modstander")
                        },
                        textStyle = TextStyle(color = PrimaryBlue, background = PrimaryWhite, fontSize = 24.sp),
                        singleLine = true,
                        readOnly = matchStarted,
                        colors=  textFieldColors(backgroundColor = PrimaryWhite)
                    )
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
                ) {
                    Box(Modifier.fillMaxWidth()){

                        Text(
                            text = matchData.opponentGoals.toString(),
                            color = PrimaryBlue,
                            fontSize = 24.sp,

                            modifier = Modifier.align(Alignment.CenterStart)
                            )

                        Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                            IconButton(onClick = { onTeamTwoScore(matchData.opponentGoals+1) }) {
                                Icon(Icons.Default.Add, contentDescription = "Increment")
                            }
                            IconButton(onClick = { onTeamTwoScore(matchData.opponentGoals-1) }) {
                                Icon(Icons.Default.Remove, contentDescription = "Decrement")
                            }
                        }

                    }

                }
            }
        }
    }
}


@Composable
fun DropdownTeamsList(teams: List<Team>, onSelectedTeam: (teamId: Int) -> Unit, TeamName: (name : String)-> Unit, teamName1: String, matchStarted: Boolean ) {
    var expanded by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf(0) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Row(modifier = Modifier
        .clickable(onClick = {
            if (!matchStarted){
                expanded = true
            }

            else
                Toast.makeText(ServiceLocator.application, "Hold kan ikke ændres under kamp", Toast.LENGTH_LONG).show()
            })
        .background(
            PrimaryWhite
        )
    ) {
        TextField(
            value = teamName1,
            onValueChange = {
                TeamName(it)
                            },
            placeholder = {
                Text(text = "Vælg hold")}
            ,
            textStyle = Typography.body1.copy(
                fontSize = 24.sp,
                color = PrimaryBlue),
            trailingIcon = {
                Icon(icon, "" , Modifier.clickable{
                    if (!matchStarted)
                    expanded = !expanded

                    else  Toast.makeText(ServiceLocator.application, "Hold kan ikke ændres under kamp", Toast.LENGTH_LONG).show()
                }
                )
            },
            enabled = false,
            readOnly = matchStarted,
            colors = textFieldColors(backgroundColor = PrimaryWhite)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PrimaryWhite
                ),
        ) {
            teams.forEach { team ->
                DropdownMenuItem(onClick = {
                    selectedId = team.teamId-1
                    onSelectedTeam(team.teamId)
                    expanded = false
                }) {
                    Text(team.clubName, color = PrimaryBlue, fontSize = 24.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun TeamComponentPreview(){
   /* TeamComponent(
        defaultDummyMatchData[0],
        teams = defaultTeamDummyData,
        onSelectedTeamOne = { },
        onTeamTwoName = { },
        onTeamTwoScore = { },
    )*/
}