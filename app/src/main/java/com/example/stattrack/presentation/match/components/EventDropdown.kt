package com.example.stattrack.presentation.match

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stattrack.model.model.Player
import com.example.stattrack.presentation.match.data.EventItems
import com.example.stattrack.presentation.match.data.PlayerItems
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite


@Composable
fun DropdownMenu(
    colorSelected: Color = PrimaryBlue,
    colorBackground: Color = PrimaryWhite,
    expanded: Boolean,
    selectedIndex: Int,
    eventItems: List<EventItems>?,
    playerItems: List<Player>?,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Box {
        content()
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(
                    color = colorBackground,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            if(eventItems != null)
                eventItems.forEachIndexed { index, s ->
                    if (selectedIndex == index) {
                        DropdownMenuItem(modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(1.dp, colorSelected))
                            .background(
                                color = colorBackground,
                                shape = RoundedCornerShape(16.dp)
                            ),
                            onClick = { onSelect(index) }
                        ) {
                            Text( text = s.title,
                                color = PrimaryBlue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                        }
                    } else {
                        DropdownMenuItem(modifier = Modifier
                            .fillMaxWidth(),
                            onClick = { onSelect(index) }
                        ) {
                            Text( text = s.title,
                                color = PrimaryBlue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                        }
                    }
                }
            else if (playerItems != null)
                playerItems.forEachIndexed { index, s ->
                    if (selectedIndex == index) {
                        DropdownMenuItem(modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(1.dp, colorSelected))
                            .background(
                                color = colorBackground,
                                shape = RoundedCornerShape(16.dp)
                            ),
                            onClick = { onSelect(index) }
                        ) {
                            Text( text = s.name,
                                color = PrimaryBlue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                        }
                    } else {
                        DropdownMenuItem(modifier = Modifier
                            .fillMaxWidth(),
                            onClick = { onSelect(index) }
                        ) {
                            Text( text = s.name,
                                color = PrimaryBlue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                        }
                    }
                }
        }
    }
}

