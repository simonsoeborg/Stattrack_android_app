package com.example.stattrack.presentation

import android.os.Bundle
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.presentation.team.MyTeamsScreen
import com.example.stattrack.presentation.team.SpecificTeamScreen
import com.example.stattrack.presentation.team.TeamViewModel
import com.example.stattrack.presentation.match.MatchScreen
import com.example.stattrack.presentation.match.MatchViewModel
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.di.ServiceLocator.matchViewModel
import com.example.stattrack.di.ServiceLocator.prepopulateSQLiteDB
import com.example.stattrack.di.ServiceLocator.specificTeamViewModel
import com.example.stattrack.di.ServiceLocator.teamViewModel
import com.example.stattrack.model.model.Team
import com.example.stattrack.presentation.team.SpecificTeamViewModel
import com.example.stattrack.presentation.ui.theme.StattrackTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val matchVM: MatchViewModel by lazy { matchViewModel }
        val teamVM: TeamViewModel by lazy { teamViewModel }
        val sTeamVM: SpecificTeamViewModel by lazy {specificTeamViewModel}


        supportActionBar?.hide() // Hide the title bar so the app shows in fullscreen
        /* For development purposes */
        prepopulateSQLiteDB()

        setContent {
            val navController = rememberNavController()
            StattrackTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) })
                {
                    NavHost(navController, startDestination = NavItem.Landing.route)
                    {
                        composable(NavItem.Landing.route) {
                            LandingScreen(navController)
                        }
                        composable(NavItem.Team.route) {
                            MyTeamsScreen(teamViewModel = teamVM, navController)
                        }
                        composable(NavItem.Match.route,) {
                            MatchScreen(matchViewModel = matchVM, navController)
                        }
                        composable(NavItem.SpecifikTeam.route){

                            val teamObject = navController.previousBackStackEntry?.arguments?.getParcelable<Team>("specifikTeam")
                            if (teamObject != null) {
                                SpecificTeamScreen(navController = navController,team = teamObject, teamViewModel = sTeamVM)
                            }

                            if(teamObject == null){
                                println("TeamObject er null")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem.Team,
        NavItem.Match
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
                        navController.graph.startDestinationRoute?.let {/*
                                route -> popUpTo(route) {
                            saveState = true
                        }*/
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}



