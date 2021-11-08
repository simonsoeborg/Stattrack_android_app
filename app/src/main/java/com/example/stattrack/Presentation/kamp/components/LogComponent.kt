package com.example.stattrack.Presentation.kamp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stattrack.Presentation.kamp.data.LogItems
import com.example.stattrack.Presentation.ui.theme.PrimaryBlue

@Composable
fun LogComponent() {
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(200.dp)
        .border(BorderStroke(1.dp, PrimaryBlue))) {
        Row(modifier = Modifier.padding(5.dp)) {
            LogMessages(messages = listOf(
                LogItems.LogGoal,
                LogItems.LogSave,
                LogItems.LogAssist,
                LogItems.LogAttempt,
                LogItems.LogEjection,
                LogItems.LogYellow,
                LogItems.LogRed
            ))
        }
    }
}

@Composable
fun LogMessages(messages: List<LogItems>) {
    Column() {
        messages.forEach{messages ->
            Text(messages.entry, textAlign = TextAlign.Center, modifier = Modifier.padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogComponentPreview() {
    LogComponent()
}