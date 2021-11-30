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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.di.ServiceLocator
import com.example.stattrack.presentation.ui.theme.PrimaryBlue


@Composable
fun StopWatchComponent(
    isRunning: State<Boolean>,
    timeElapsed: State<String>,
    onPlayPressed: () -> Unit,
    onStopPressed: () -> Unit,
    properTeam1 : State<Boolean>,
    properTeam2 : State<Boolean>
) {


    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row( modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(0.50F)) {
            // Timer
            Text(
                timeElapsed.value,
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

                if (!properTeam1.value && !properTeam2.value){
                    Toast.makeText(ServiceLocator.application, "Hov! Udfyld først hold og modstander", Toast.LENGTH_LONG).show()
                }

                else
                onPlayPressed() }){
                Icon(
                    if(!isRunning.value) {
                        Icons.Default.PlayCircle
                    } else Icons.Default.Pause,

                     contentDescription =
                     if(!isRunning.value){
                         "Play"
                     } else "Pause",

                    tint = PrimaryBlue
                )
            }

            IconButton(onClick = { onStopPressed() }) {
                Icon(Icons.Default.StopCircle, contentDescription = "Stop", tint = PrimaryBlue)
            }
        }
    }
}


