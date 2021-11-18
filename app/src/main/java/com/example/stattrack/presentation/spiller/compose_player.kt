package com.example.stattrack.presentation.spiller

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stattrack.presentation.ui.theme.StattrackTheme

@Composable
fun PlayerClass(){
    Column(modifier = Modifier.padding(10.dp), )
    {
        Box(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.align(Alignment.CenterEnd)) {
            Text(
                text = "Simon Lindhard Fridolf",
                modifier = Modifier.padding(end = 20.dp),
                fontSize = 18.sp)

            Text(
                text = "Målmand",
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 15.sp)
        }
        }

        PlayerData(9,5,20,7)
    }
}

@Composable
fun PlayerData(antalKamp: Int, antalMaal: Int, antalSkud: Int, antalAssist: Int) {

    val padding = 10.dp

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier
                    .weight(1f)
            ) {
                SpillerInfoText(padding)
            }

            Column(
                Modifier
                    .weight(1f)
            ) {
                SpillerInfoDenne(
                    padding = padding,
                    antalMaal = antalMaal,
                    antalKamp = antalKamp,
                    antalAssist = antalAssist,
                    antalSkud = antalSkud
                )
            }
        }


    }
}


@Composable
fun SpillerInfoText (padding: Dp) {

    Text(text = "Antal kampe spillede:", Modifier.padding(padding))

    Text(text = "Antal mål:", Modifier.padding(padding))

    Text(text = "Antal skudforsøg:", Modifier.padding(padding))

    Text(text = "Antal assists:", Modifier.padding(padding) )
}

@Composable
fun SpillerInfoDenne(padding: Dp, antalMaal: Int, antalKamp: Int, antalAssist: Int, antalSkud: Int) {
    Text( text = "   $antalKamp   ",
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier.padding(padding))

    Text(
        text = "   $antalMaal   ",
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier.padding(padding))
    Text(
        text = "   $antalSkud   ",
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier.padding(padding))

    Text(
        text = "   $antalAssist   ",
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier.padding(padding))
}

@Composable
fun BarChartView() {
    // Todo Lav graph implementation af data.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StattrackTheme {
        PlayerClass()

    }
}