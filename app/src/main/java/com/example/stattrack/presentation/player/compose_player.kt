package com.example.stattrack.presentation.player


import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stattrack.model.model.Player
import com.example.stattrack.model.model.PlayerStats
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.presentation.ui.theme.StattrackTheme
import coil.compose.rememberImagePainter


@Composable
fun PlayerClass(navController: NavHostController, playerViewModel: PlayerViewModel, player : Player){

    playerViewModel.loadPlayersStats(player.id)
    val playerStats : State<List<PlayerStats>> = playerViewModel.playerStats.collectAsState()

    playerViewModel.combineStats(playerStats.value)
    val combinedPlayerStats : State<PlayerStats> = playerViewModel.combinedPlayerStats.collectAsState()
    val imgString : State<String> = playerViewModel.imgString.collectAsState()
    val gamesTotal : State<Int> = playerViewModel.gamesTotal.collectAsState()

    PlayerClassContent(
        combinedPlayerStats.value,
        gamesTotal.value,
        player,
        playerViewModel,
        navController,
        imgString
    )
}

@Composable
fun PlayerClassContent(
    stats : PlayerStats,
    gamesAmount : Int,
    player : Player,
    playerViewModel: PlayerViewModel,
    navController: NavHostController,
    imgString : State<String>
){
    Column(modifier = Modifier.padding(10.dp))
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
                    modifier = Modifier
                        .padding(start = 20.dp, top = 50.dp)
                        .rotate(90f),
                    fontSize = 20.sp,
                    color = PrimaryBlue)
            }
        }
        
        PlayerData(gamesAmount, stats.goals,stats.attempts,stats.assists)
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){

            RemovePlayerButton(
                navController = navController,
                player = player ,
                playerViewModel = playerViewModel
            )
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){

            PlayerStatLineChart(imgString.value)
        }

    }
}

@Composable
fun PlayerStatLineChart(imageString: String ) {
    Image(
        painter = rememberImagePainter(imageString),
        contentDescription = null,
        modifier = Modifier.size(400.dp)
    )
    println(imageString)
    println(imageString)
    println(imageString)
    println(imageString)
    println(imageString)
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
fun RemovePlayerButton(navController: NavHostController, player: Player, playerViewModel: PlayerViewModel){
    var showDialog by remember { mutableStateOf(false) }
    Button(

        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        shape = shapes.medium,
        onClick = {
        showDialog = true
        },

        modifier= Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(0.6f)



    ) {
        Text(text = "Slet spiller", color = PrimaryWhite)
    }
    if (showDialog){
        AlertDialog(
            backgroundColor = PrimaryWhite,
            onDismissRequest = { showDialog = false},
            title = {Text("Slet spiller?", color = PrimaryBlue, fontSize = 30.sp) },
            text = {Text("Er du sikker på at du vil slette ${player.name} ?", color = PrimaryBlue) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    onClick = {
                        playerViewModel.deletePlayer(player.id)
                        navController.navigateUp()
                    }) {
                    Text(text = "Slet", color = PrimaryWhite)
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier.padding(end=10.dp),
                    onClick = { showDialog = false }) {
                    Text("Annuller")
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StattrackTheme {
        //PlayerClassContent()

    }
}