package com.example.stattrack.presentation.match

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stattrack.presentation.match.data.EventItems
import com.example.stattrack.presentation.match.data.PlayerItems
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite


@Composable
fun EventComponent() {
    var expandedEvents by remember { mutableStateOf(false) }
    var selectedIndexEvents by remember { mutableStateOf(0) }
    var expandedPlayers by remember { mutableStateOf(false) }
    var selectedIndexPlayers by remember { mutableStateOf(0) }

    val eventItems = listOf(
        EventItems.Default,
        EventItems.EventGoal,
        EventItems.EventAttempt,
        EventItems.EventSave,
        EventItems.EventAssist,
        EventItems.EventEjection,
        EventItems.EventYellow,
        EventItems.EventRed
    )

    val dummyDataPlayerItems = listOf(
        PlayerItems.DefaultPlayer,
        PlayerItems.DefaultPlayer1,
        PlayerItems.DefaultPlayer2,
        PlayerItems.DefaultPlayer3,
        PlayerItems.DefaultPlayer4,
        PlayerItems.DefaultPlayer5,
        PlayerItems.DefaultPlayer6,
        PlayerItems.DefaultPlayer7,
        PlayerItems.DefaultPlayer8,
        PlayerItems.DefaultPlayer9,
        PlayerItems.DefaultPlayer10,
        PlayerItems.DefaultPlayer11,
        PlayerItems.DefaultPlayer12
    )

    // DUMMY DATA FOR PLAYERS

    val buttonTitleEvents = eventItems[selectedIndexEvents].title
    val buttonTitlePlayers = dummyDataPlayerItems[selectedIndexPlayers].name


    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row() {
            Column() {
                Row( modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(Alignment.CenterHorizontally)) {
                    // Select Player
                    DropdownMenu(
                        expanded = expandedPlayers,
                        selectedIndex = selectedIndexPlayers,
                        eventItems = null,
                        playerItems = dummyDataPlayerItems,
                        onSelect = { index ->
                            selectedIndexPlayers = index
                            expandedPlayers = false
                        },
                        onDismissRequest = { expandedPlayers = false }
                    ) {
                        OutlinedButton(
                            onClick = {
                                expandedPlayers = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(PrimaryWhite)
                                .border(BorderStroke(1.dp, PrimaryBlue))
                        ) {
                            Text(
                                text = buttonTitlePlayers,
                                color = PrimaryBlue,
                                maxLines = 1
                            )
                        }
                    }
                }
                Row(modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .align(Alignment.CenterHorizontally)) {
                    DropdownMenu(
                        colorSelected = PrimaryBlue,
                        expanded = expandedEvents,
                        selectedIndex = selectedIndexEvents,
                        eventItems = eventItems,
                        playerItems = null,
                        onSelect = { index ->
                            selectedIndexEvents = index
                            expandedEvents = false
                        },
                        onDismissRequest = {
                            expandedEvents = false
                        })
                    {
                        OutlinedButton(
                            onClick = {
                                expandedEvents = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(PrimaryWhite)
                                .border(BorderStroke(1.dp, PrimaryBlue))
                        ) {
                            Text(
                                text = buttonTitleEvents,
                                color = PrimaryBlue,
                                maxLines = 1
                            )
                        }
//                        OutlinedButton(onClick = { expandedEvents = true },
//                            modifier = Modifier.size(100.dp),
//                            shape = CircleShape,
//                            border = BorderStroke(1.dp, PrimaryBlue)
//                        ) {
//                            Icon(Icons.Default.Add, contentDescription = "New Event", tint = PrimaryBlue)
//                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventComponentPreview() {
    EventComponent()
}