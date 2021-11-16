package com.example.stattrack.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.presentation.hold.HoldScreen
import com.example.stattrack.presentation.match.MatchScreen
import com.example.stattrack.presentation.match.MatchViewModel
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.services.ServiceLocator.matchViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val matchVM: MatchViewModel by lazy { matchViewModel }

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                NavHost(navController, startDestination = NavItem.Hold.route) {
                    composable(NavItem.Hold.route) {
                        HoldScreen()
                    }
                    composable(NavItem.Kamp.route) {
                        MatchScreen(matchViewModel = matchVM)
                    }
                    composable(NavItem.Spiller.route) {
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem.Hold,
        NavItem.Kamp
    )
    BottomNavigation(
        backgroundColor = PrimaryWhite,
        contentColor = PrimaryBlue
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                                route -> popUpTo(route) {
                            saveState = true
                        }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                }
            )
        }
    }
}


