package com.example.stattrack.View.navbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stattrack.View.ui.theme.PrimaryBlue
import com.example.stattrack.View.ui.theme.PrimaryWhite
import com.example.stattrack.View.ui.theme.StattrackTheme
import org.intellij.lang.annotations.JdkConstants

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StattrackTheme {
        build_navbar()
    }
}

@Composable
fun build_navbar() {
    val padding = 10.dp
    val margin = 25.dp

    Column() {
        Row(
        )
        {
            Column ( horizontalAlignment = Alignment.CenterHorizontally )
            {
                navbtns(padding = padding)
            }
        }
    }
}

@Composable
fun navbtns(padding: Dp) {
    Row {
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = padding),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryWhite),
            border = BorderStroke(0.5.dp, PrimaryBlue)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.SportsHandball,
                    contentDescription = "Kamp",
                    tint = PrimaryBlue
                )
                Text(text = "KAMP", color = PrimaryBlue)
            }
        }
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryWhite),
            border = BorderStroke(0.5.dp, PrimaryBlue)) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.People,
                    contentDescription = "Hold",
                    tint = PrimaryBlue
                )
                Text(text = "HOLD", color = PrimaryBlue)
            }
        }
    }

}