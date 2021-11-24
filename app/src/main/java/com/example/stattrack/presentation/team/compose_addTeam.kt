package com.example.stattrack.presentation.team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.navbar.Screen


@Composable
fun AddTeam(navController: NavHostController, onSubmitPressed: (Team) -> Unit ) {
    var placeholderClubName = "Not updated yet"
    var placeholderTeamName = "Not updated yet"
    var placeholderCreatorName = "Not updated yet"
    var placeholderTeamUYear = "Not updated yet"
    var placeholderDivision = "Not updated yet"

    // TODO : Remember to create unique ID in DB when passing event to ViewModel

    Column( modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.padding(all = 10.dp)) {
            OutlinedTextField(
                value = "Klub navn",
                onValueChange = { placeholderClubName = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        }
        Row(modifier = Modifier.padding(all = 10.dp)) {
            OutlinedTextField(
                value = "Hold navn",
                onValueChange = { placeholderTeamName = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        }
        Row() {
            OutlinedTextField(
                value = "Træner navn",
                onValueChange = { placeholderCreatorName = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                )
        }
        Row(modifier = Modifier.padding(all = 10.dp)) {
            OutlinedTextField(
                value = "Årgang",
                onValueChange = { placeholderTeamUYear = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                )
        }
        Row(modifier = Modifier.padding(all = 10.dp)) {
             OutlinedTextField(
                 value = "Division",
                 onValueChange = { placeholderDivision = it },
                 textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
             )
         }
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Button(onClick = { // TODO Implementér logik der validerer input (not-null, osv)

                onSubmitPressed(
                    Team(
                        1000,
                        placeholderTeamName,
                        placeholderClubName,
                        placeholderCreatorName,
                        placeholderTeamUYear,
                        placeholderDivision))
                        navController.navigate("Team")
            }

            ) { Text("Opret Hold") }
        }

    }
}



@Preview
@Composable
fun AddTeamPreview(){

}