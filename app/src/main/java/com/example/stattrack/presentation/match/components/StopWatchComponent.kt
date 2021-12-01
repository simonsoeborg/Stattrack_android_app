package com.example.stattrack.presentation.match.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.di.ServiceLocator
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite


@Composable
fun StopWatchComponent(
    matchStarted: Boolean,
    isRunning: Boolean,
    timeElapsed: String,
    onPlayPressed: () -> Unit,
    onStopPressed: () -> Unit,
    properTeam1 : Boolean,
    properTeam2 : Boolean
) {
    var showDialog by remember { mutableStateOf(false) }

    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row( modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(0.50F)) {
            // Timer
            Text(
                timeElapsed,
                color = PrimaryBlue,
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        ) {
            IconButton(onClick = {

                if (!properTeam1 && !properTeam2){
                    Toast.makeText(ServiceLocator.application, "Hov! Udfyld først hold og modstander", Toast.LENGTH_LONG).show()
                }

                else
                onPlayPressed() }){
                Icon(
                    if(!isRunning) {
                        Icons.Default.PlayCircle
                    } else Icons.Default.Pause,

                     contentDescription =
                     if(!isRunning){
                         "Play"
                     } else "Pause",

                    tint = PrimaryBlue
                )
            }

            IconButton(onClick = {
                if (matchStarted){
                    showDialog = true
                }
            }) {
                Icon(Icons.Default.StopCircle, contentDescription = "Stop", tint = PrimaryBlue)
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            backgroundColor = PrimaryWhite,
            onDismissRequest = { showDialog = false },
            title = { Text("Stop kamp?", color = PrimaryBlue, fontSize = 30.sp) },
            text = {
                Text(
                    "Er du sikker på at du vil stoppe kampen ?",
                    color = PrimaryBlue
                )
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    onClick = {
                        onStopPressed()
                        showDialog = false
                    }) {
                    Text(text = "Stop kampen", color = PrimaryWhite)
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { showDialog = false }) {
                    Text("Annuller")
                }
            })
    }
}


