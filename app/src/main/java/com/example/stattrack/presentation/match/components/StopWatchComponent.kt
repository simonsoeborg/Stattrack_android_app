package com.example.stattrack.presentation.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stattrack.presentation.ui.theme.PrimaryBlue


@Composable
fun StopWatchComponent(timeValue: String) {
    var time by remember { mutableStateOf( timeValue ) }
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row( modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(0.50F)) {
            // Timer
            TextField(value = time,
                onValueChange = { time = it },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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