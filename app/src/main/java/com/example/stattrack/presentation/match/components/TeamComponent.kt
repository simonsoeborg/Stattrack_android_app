package com.example.stattrack.presentation.match.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite


@Composable
fun TeamComponent(hold1_name: String, hold2_name: String, hold1_sc: String, hold2_sc: String, landscape: Boolean) {
    var hold1_navn by remember { mutableStateOf(hold1_name) }
    var hold1_score by remember { mutableStateOf(hold1_sc) }
    var hold2_navn by remember { mutableStateOf( hold2_name) }
    var hold2_score by remember { mutableStateOf(hold2_sc) }

    if (landscape) {
        TeamComponentLandscape(hold1_navn_ = "", hold2_navn_ = "", hold1_score_ = "", hold2_score_ = "")
    } else {
        TeamComponentPortrait(
            hold1_navn_ = hold1_navn,
            hold2_navn_ = hold1_score,
            hold1_score_ = hold2_navn,
            hold2_score_ = hold2_score
        )
    }
}

@Composable
fun TeamComponentLandscape(hold1_navn_: String, hold2_navn_: String, hold1_score_: String, hold2_score_: String) {
    var hold1_navn by remember { mutableStateOf(hold1_navn_) }
    var hold1_score by remember { mutableStateOf(hold1_score_) }
    var hold2_navn by remember { mutableStateOf(hold2_navn_) }
    var hold2_score by remember { mutableStateOf(hold2_score_) }

    Column( modifier = Modifier.fillMaxWidth()) {
        // Teams and Scores
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)){
            Column(modifier = Modifier.weight(0.7F)) {
                TextField(
                    value = hold1_navn,
                    onValueChange = { hold1_navn = it },
                    label = { Text("Hold 1") },
                    modifier = Modifier
                        .border(BorderStroke(1.dp, PrimaryBlue))
                        .background(
                            PrimaryWhite
                        )
                )
            }
            Divider(modifier = Modifier.width(5.dp))
            Column(modifier = Modifier.weight(0.3F)) {
                TextField(value = hold1_score,
                    onValueChange = { hold1_score = it},
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .border(BorderStroke(1.dp, PrimaryBlue))
                        .background(
                            PrimaryWhite
                        )
                )
            }
            Divider(modifier = Modifier.width(30.dp))
            Column(modifier = Modifier.weight(0.3F)) {
                TextField(value = hold2_score,
                    onValueChange = { hold2_score = it},
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .border(BorderStroke(1.dp, PrimaryBlue))
                        .background(
                            PrimaryWhite
                        )
                )
            }
            Divider(modifier = Modifier.width(5.dp))
            Column(modifier = Modifier.weight(0.7F)) {
                TextField(
                    value = hold2_navn,
                    onValueChange = { hold2_navn = it },
                    label = { Text("Hold 2") },
                    modifier = Modifier
                        .border(BorderStroke(1.dp, PrimaryBlue))
                        .background(
                            PrimaryWhite
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamComponentLandscapePreview() {
    TeamComponentLandscape("", "", "", "")
}

@Composable
fun TeamComponentPortrait(hold1_navn_: String, hold2_navn_: String, hold1_score_: String, hold2_score_: String) {
    var hold1_navn by remember { mutableStateOf(hold1_navn_) }
    var hold1_score by remember { mutableStateOf(hold1_score_) }
    var hold2_navn by remember { mutableStateOf( hold2_navn_) }
    var hold2_score by remember { mutableStateOf(hold2_score_) }
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