package com.example.stattrack.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.stattrack.R
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.delay

private const val SplashWaitTime = 3500L

@Composable
fun LandingScreen (navController: NavController/*onTimeout: () -> Unit*/) {
    //val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(top = 100.dp)) {
            Text(
                text = "StatTrack",
                color = PrimaryBlue,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
            )
        }

        Row{

            val compositionResult: LottieCompositionResult = rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    R.raw.handball
                )
            )
            val progress by animateLottieCompositionAsState(
                composition = compositionResult.value,
                isPlaying = true,
                iterations = 1,
                speed = 1.0f
            )

            LottieAnimation(composition = compositionResult.value, progress = progress)

            LaunchedEffect(key1 = true) {
                delay(SplashWaitTime)
                navController.navigate(NavItem.Team.route){
                    popUpTo(NavItem.Team.route) { inclusive = true }
                }
            }
        }
    }
}