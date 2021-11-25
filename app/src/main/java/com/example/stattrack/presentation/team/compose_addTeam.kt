package com.example.stattrack.presentation.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.navbar.Screen
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.presentation.ui.theme.Typography


@Composable
fun AddTeam(navController: NavHostController?, onSubmitPressed: (Team) -> Unit ) {
    var placeholderClubName = TextFieldValue("Klub navn")
    var placeholderTeamName = "Not updated yet"
    var placeholderCreatorName = "Not updated yet"
    var placeholderTeamUYear = "Not updated yet"
    var placeholderDivision = "Not updated yet"

    // TODO : Remember to create unique ID in DB when passing event to ViewModel

    Column( modifier = Modifier
        .fillMaxWidth()
        .background(color = PrimaryWhite)
    ){
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = placeholderClubName,
                onValueChange = { placeholderClubName = it },
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
                value = "Hold navn",
                onValueChange = { placeholderTeamName = it },
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
                value = "Træner navn",
                onValueChange = { placeholderCreatorName = it },
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
                value = "Årgang",
                onValueChange = { placeholderTeamUYear = it },
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
                 value = "Division",
                 onValueChange = { placeholderDivision = it },
                 textStyle = Typography.body1.copy(
                     textAlign = TextAlign.Center,
                     color = PrimaryBlue)
             )
         }
        Row(modifier = Modifier
            .padding(all = 10.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { // TODO Implementér logik der validerer input (not-null, osv)
                    onSubmitPressed(
                    Team(
                        1000,
                        placeholderTeamName,
                        placeholderClubName.text,
                        placeholderCreatorName,
                        placeholderTeamUYear,
                        placeholderDivision))
                        navController?.navigate("Team")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue, contentColor = PrimaryWhite)

            ) { Text("Opret Hold") }
        }

    }
}



@Preview
@Composable
fun AddTeamPreview(){
    AddTeam(navController = null, onSubmitPressed = {})
}