package com.example.stattrack.presentation.match.components


import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultTeamDummyData
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite


@Composable
fun TeamComponent(
    teamOneScore: Int,
    teamTwoScore: Int,
    teams:List<Team>,
    onSelectedTeam: (teamId: Int) -> Unit,
    onTeamTwoName: (String) -> Unit,
    onTeamTwoScore: (Int) -> Unit){

    val teamTwoName = remember { mutableStateOf("Hold 2")}

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
            Row( modifier = Modifier
                .padding(2.dp)
            )
            {
                Column( modifier = Modifier
                    .weight(3f)
                    .padding(1.dp)
                ) {
                    DropdownTeamsList(
                        teams = teams,
                        onSelectedTeam = { onSelectedTeam(it) }
                    )
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .padding(1.dp)
                ) {
                    Text(
                        text = teamOneScore.toString(),
                        color = PrimaryBlue,
                        fontSize = 24.sp,
                    )
                }
            }

            // Team 2 row
            Row( modifier = Modifier
                .padding(2.dp).background(color = PrimaryWhite)
            )
            {
                Column( modifier = Modifier
                    .weight(3f)
                    .padding(1.dp)
                    .background(color = PrimaryWhite)
                    //.border(2.dp, color = PrimaryBlue) <- doesnt look good find another style
                ) {
                    BasicTextField(
                        value = teamTwoName.value,
                        onValueChange = {
                            teamTwoName.value = it
                            onTeamTwoName(teamTwoName.value)
                        },cursorBrush = SolidColor(Transparent),
                        textStyle = TextStyle(color = PrimaryBlue, background = PrimaryWhite, fontSize = 24.sp),
                        singleLine = true
                        /*colors = textFieldColors(
                            focusedIndicatorColor = Transparent,
                            disabledIndicatorColor = Transparent,
                            unfocusedIndicatorColor = Transparent
                        ) */

                        )
                        /*
                        colors =  textFieldColors(backgroundColor = PrimaryWhite) */


                }
                Column( modifier = Modifier
                    .weight(1f)
                    .padding(1.dp)
                ) {
                    // TODO - Make a button so we can increment score on team 2 and use callback function onTeamTwoScore(Int) to call value upwards in compose-tree
                    Text(
                        text = teamTwoScore.toString(),
                        color = PrimaryBlue,
                        fontSize = 24.sp,

                    )
                }
            }
        }
    }
}


@Composable
fun DropdownTeamsList(teams: List<Team>, onSelectedTeam: (teamId: Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf(0) }

    Row(modifier = Modifier
        .clickable(onClick = { expanded = true })
        .background(
            PrimaryWhite
        )
    ) {
        Text(
            teams[selectedId].clubName,
            color = PrimaryBlue,
            fontSize = 24.sp
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
    TeamComponent(
        25,
        0,
        teams = defaultTeamDummyData,
        onSelectedTeam = { },
        onTeamTwoName = { },
        onTeamTwoScore = { }

    )
}