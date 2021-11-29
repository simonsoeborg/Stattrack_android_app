package com.example.stattrack.presentation.match

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stattrack.model.model.EventData
import com.example.stattrack.model.model.defaultDummyEventData
import com.example.stattrack.presentation.ui.theme.PrimaryBlue

@Composable
fun LogComponent(
    events: List<EventData>
) {
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        .border(BorderStroke(1.dp, PrimaryBlue))) {
        Row(modifier = Modifier.padding(5.dp)) {
            LazyColumn() {
                items(events){event ->
                    Text(
                        "${event.time} | ${event.playerName} | ${event.eventType}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LogComponentPreview() {
    LogComponent(defaultDummyEventData
    )
}