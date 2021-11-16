package com.example.stattrack.presentation.hold

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.model.model.Team
import com.example.stattrack.model.model.defaultTeamDummyData
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import kotlin.coroutines.coroutineContext

@Composable
fun HoldScreen() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .align(alignment = CenterHorizontally)
        ) {
            Column {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "Hold oversigt", fontSize = 32.sp, color = PrimaryBlue)
                        Column(modifier = Modifier.padding(10.dp)) {
                            TeamList(defaultTeamDummyData)
                        }
                    }
                }
                Row() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "Kamp oversigt", fontSize = 32.sp, color = PrimaryBlue)
                        Column(modifier = Modifier.padding(10.dp)) {
                            dummydata2()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun dummydata1() {
    val items = listOf("HØJ U19", "HØJ Elite", "HØJ 2", "HØJ 3")

    items.forEach { item ->
        Text(text = "$item", modifier = Modifier.padding(2.dp), color = PrimaryBlue)
    }
}


@Composable
fun TeamList(teams: List<Team> ) {
    LazyColumn() {
        items(
            items = teams,
            key = { team ->
                // Return a stable + unique key for the item
                team.teamId
            },

        ) {
                    team ->
            Surface(modifier = Modifier.clickable { println(team.name + team.teamId)}){
                    Text(team.name,modifier = Modifier.padding(2.dp), color = PrimaryBlue)
            }
        }
    }
}





@Composable
fun dummydata2() {
    val items = listOf("HØJ U19 mod FIF U19", "HØJ Elite mod BSV", "HØJ 2 mod Randers", "HØJ 3 mod Rudersdal")

    items.forEach { item ->
        Text(text = "$item", modifier = Modifier.padding(2.dp), color = PrimaryBlue)
    }
}

@Preview(showBackground = true)
@Composable
fun HoldScreenPreview() {
    HoldScreen()
}
