package com.example.stattrack.presentation.kamp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TeamComponent(hold1_name: String, hold2_name: String, hold1_sc: String, hold2_sc: String) {
    var hold1_navn by remember { mutableStateOf(hold1_name) }
    var hold1_score by remember { mutableStateOf(hold1_sc) }
    var hold2_navn by remember { mutableStateOf( hold2_name) }
    var hold2_score by remember { mutableStateOf(hold2_sc) }
    Row( // Teams Row
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( // Main Column
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp)
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

