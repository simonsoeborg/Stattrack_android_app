package com.example.stattrack.presentation.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.PlayerStats
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.StattrackTheme

@Composable
fun PlayerClass(navController: NavHostController, playerViewModel: PlayerViewModel, player : Player){

    playerViewModel.loadPlayersStats(player.id)
    val playerStats : State<List<PlayerStats>> = playerViewModel.playerStats.collectAsState()

    playerViewModel.combineStats(playerStats.value)
    val combinedPlayerStats : State<PlayerStats> = playerViewModel.combinedPlayerStats.collectAsState()
    val gamesTotal : State<Int> = playerViewModel.gamesTotal.collectAsState()

    PlayerClassContent(combinedPlayerStats, gamesTotal, player)
}

@Composable
fun PlayerClassContent(stats : State<PlayerStats> , gamesAmount : State<Int>, player : Player){
    Column(modifier = Modifier.padding(10.dp), )
    {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = player.name,
                    modifier = Modifier.padding(end = 20.dp),
                    fontSize = 26.sp,
                    color = PrimaryBlue)

                Text(
                    text = player.position,
                    modifier = Modifier.padding(end = 10.dp),
                    fontSize = 20.sp,
                    color = PrimaryBlue,
                    fontStyle = FontStyle.Italic)
            }
        }
        
        PlayerData(gamesAmount.value, stats.value.goals,stats.value.attempts,stats.value.assists)
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
                PlayerInfoText(padding)
            }

            Column(
                Modifier
                    .weight(1f)
            ) {
                PlayerInfoThis(
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
fun PlayerInfoText (padding: Dp) {

    Text(text = "Antal kampe spillede:", Modifier.padding(padding))

    Text(text = "Antal mål:", Modifier.padding(padding))

    Text(text = "Antal skudforsøg:", Modifier.padding(padding))

    Text(text = "Antal assists:", Modifier.padding(padding) )
}

@Composable
fun PlayerInfoThis(padding: Dp, antalMaal: Int, antalKamp: Int, antalAssist: Int, antalSkud: Int) {
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
        //PlayerClassContent()

    }
}