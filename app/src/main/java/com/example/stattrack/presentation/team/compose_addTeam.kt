package com.example.stattrack.presentation.team

import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultTeamDummyData

@Composable
fun AddTeam(team : Team, onSubmitPressed: (team: Team) -> Unit ) {
    var placeholderClubName = "Not updated yet"
    var placeholderTeamName = "Not updated yet"
    var placeholderCreatorName = "Not updated yet"
    var placeholderTeamUYear = "Not updated yet"
    var placeholderDivision = "Not updated yet"

    val teamId : Int // create unique id in DB
    val creatorId : String // create unique id in DB


    OutlinedTextField(value = "Klub navn", onValueChange = { placeholderClubName = it})
    OutlinedTextField(value = "Hold navn", onValueChange = { placeholderTeamName = it})
    OutlinedTextField(value = "Træner navn", onValueChange = { placeholderCreatorName = it})
    OutlinedTextField(value = "Årgang", onValueChange = { placeholderTeamUYear = it})
    OutlinedTextField(value = "Division", onValueChange = { placeholderDivision = it})
    Button(onClick = { // TODO Implementér logik der validerer input (not-null, osv)
        onSubmitPressed(
            Team(
                1000,
                placeholderTeamName,
                placeholderClubName,
            "1000",
                placeholderTeamUYear,
                placeholderDivision))}
    ) {
        Text("Opret Hold")
    }

}


@Preview
@Composable
fun AddTeamPreview(){
    val team = defaultTeamDummyData[1]

    AddTeam(team = team, onSubmitPressed ={} )
}