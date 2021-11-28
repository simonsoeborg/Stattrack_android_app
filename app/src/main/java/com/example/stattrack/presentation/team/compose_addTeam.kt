package com.example.stattrack.presentation.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun AddTeam(navController: NavHostController?, onSubmitPressed: (Team) -> Unit, onShowAddTeam: (Boolean) -> Unit ) {
  
  
  
  
    var placeholderClubName by remember { mutableStateOf("")}
    var placeholderTeamName by remember { mutableStateOf("") }
    var placeholderCreatorName by remember { mutableStateOf("") }
    var placeholderTeamUYear by remember { mutableStateOf("") }
    var placeholderDivision by remember { mutableStateOf("") }


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
                value = placeholderClubName,
                onValueChange = { placeholderClubName = it },
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
                value = placeholderTeamName,
                onValueChange = { placeholderTeamName = it },
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
                value = placeholderCreatorName,
                onValueChange = { placeholderCreatorName = it },
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
                value = placeholderTeamUYear,
                onValueChange = { placeholderTeamUYear = it },
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
                 value = placeholderDivision,
                 onValueChange = { placeholderDivision = it },
                 label = { Text("Division") },
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
                        placeholderClubName,
                        placeholderCreatorName,
                        placeholderTeamUYear,
                        placeholderDivision))
                        navController?.navigate("Team")
                    onShowAddTeam(false)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue, contentColor = PrimaryWhite)

            ) { Text("Opret Hold") }
        }

    }
}



@Preview
@Composable
fun AddTeamPreview(){
    AddTeam(navController = null, onSubmitPressed = {}, onShowAddTeam = {})
}