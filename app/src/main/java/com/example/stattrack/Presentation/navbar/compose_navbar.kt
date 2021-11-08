package com.example.stattrack.Presentation.navbar

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stattrack.Presentation.hold.HoldScreen
import com.example.stattrack.Presentation.ui.theme.PrimaryBlue
import com.example.stattrack.Presentation.ui.theme.PrimaryWhite
import androidx.compose.material.Icon
import com.example.stattrack.Presentation.kamp.KampScreen

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StattrackTheme {
        BottomNavigationBar()
    }
}*/

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

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController, startDestination = NavItem.Hold.route) {
        composable(NavItem.Hold.route) {
            HoldScreen()
        }
        composable(NavItem.Kamp.route) {
            KampScreen()
        }
        composable(NavItem.Spiller.route) {
        }
    }
}
