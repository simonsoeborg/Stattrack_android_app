package com.example.stattrack.Presentation.spiller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stattrack.Presentation.ui.theme.StattrackTheme

/*class compose_frag_spiller_data : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StattrackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FragmentSpillerData(9,5,20,7)
                }
            }
        }
    }
}*/

@Composable
fun FragmentSpillerData(antalKamp: Int, antalMaal: Int, antalSkud: Int, antalAssist: Int) {

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
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Hej")
                }
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
        FragmentSpillerData(9,5,20,7)
    }
}