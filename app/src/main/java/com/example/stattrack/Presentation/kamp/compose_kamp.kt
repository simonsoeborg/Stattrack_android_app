package com.example.stattrack.Presentation.kamp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.Presentation.ui.theme.PrimaryBlue

@Composable
fun KampScreen() {
    var hold1_navn by remember { mutableStateOf("Indtast Hold") }
    var hold1_score by remember { mutableStateOf("0")}
    var hold2_navn by remember { mutableStateOf( "Indtast Hold") }
    var hold2_score by remember { mutableStateOf("0")}
    var time by remember { mutableStateOf("00:00")}

    Column( // Main Column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth()) {
            TeamComponent(hold1_name = hold1_navn, hold2_name = hold2_navn, hold1_sc = hold1_score, hold2_sc = hold2_score)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            StopWatchComponent(time)
        }
        Row( modifier = Modifier.fillMaxWidth()) {
            EventComponent()
        }
    }
}

@Composable
fun StopWatchComponent(timeValue: String) {
    var time by remember { mutableStateOf( timeValue) }
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row( modifier = Modifier
            .align(CenterHorizontally)
            .fillMaxWidth(0.50F)) {
            // Timer
            TextField(value = time,
                onValueChange = { time = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        }
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(5.dp)
        ) {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.PlayCircle, contentDescription = "Play", tint = PrimaryBlue)
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.StopCircle, contentDescription = "Stop", tint = PrimaryBlue)
            }
        }
    }
}

@Composable
fun TeamComponent(hold1_name: String, hold2_name: String, hold1_sc: String, hold2_sc: String) {
    var hold1_navn by remember { mutableStateOf(hold1_name) }
    var hold1_score by remember { mutableStateOf(hold1_sc)}
    var hold2_navn by remember { mutableStateOf( hold2_name) }
    var hold2_score by remember { mutableStateOf(hold2_sc)}
    Row( // Teams Row
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( // Main Column
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row( modifier = Modifier
                .padding(2.dp)
            ) // Team 1 row
            {
                Column( modifier = Modifier
                    .weight(3f)
                    .padding(1.dp)
                ) {
                    TextField(
                        value = hold1_navn,
                        onValueChange = { hold1_navn = it },
                        label = { Text("Hold 1") }
                    )
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .padding(1.dp)
                ) {
                    TextField(value = hold1_score,
                        onValueChange = { hold1_score = it},
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                }
            }
            Row( modifier = Modifier
                .padding(2.dp)
            ) // Team 2 Row
            {
                Column( modifier = Modifier
                    .weight(3f)
                    .padding(1.dp)
                ) {
                    TextField(
                        value = hold2_navn,
                        onValueChange = { hold2_navn = it },
                        label = { Text("Hold 2") }
                    )
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .padding(1.dp)
                ) {
                    TextField(value = hold2_score,
                        onValueChange = { hold2_score = it},
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun EventComponent() {
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
        ) {
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                border = BorderStroke(1.dp, PrimaryBlue)) {
                Icon(Icons.Default.Add, contentDescription = "New Event", tint = PrimaryBlue)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KampScreenPreview() {
    KampScreen()
}
