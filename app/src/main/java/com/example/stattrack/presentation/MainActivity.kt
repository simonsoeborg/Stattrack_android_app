package com.example.stattrack.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.hold.HoldScreen
import com.example.stattrack.presentation.hold.TeamViewModel
import com.example.stattrack.presentation.match.MatchScreen
import com.example.stattrack.presentation.match.MatchViewModel
import com.example.stattrack.presentation.navbar.NavItem
import com.example.stattrack.presentation.ui.theme.PrimaryBlue
import com.example.stattrack.presentation.ui.theme.PrimaryWhite
import com.example.stattrack.services.ServiceLocator
import com.example.stattrack.services.ServiceLocator.matchViewModel
import com.example.stattrack.services.ServiceLocator.repository
import com.example.stattrack.services.ServiceLocator.teamViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val matchVM: MatchViewModel by lazy { matchViewModel }
        val teamVM: TeamViewModel by lazy { teamViewModel }

        prepopulateSQLiteDB()

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                NavHost(navController, startDestination = NavItem.Hold.route) {
                    composable(NavItem.Hold.route) {
                        HoldScreen(teamViewModel = teamVM)
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

fun prepopulateSQLiteDB(){
    GlobalScope.launch(Dispatchers.IO) {
        val repo = ServiceLocator.repository
        val eventData = defaultDummyEventData
        val matchData = defaultDummyMatchData
        val playerData = defaultDummyPlayerData
        val playerStatsData = defaultDummyPlayerStatsData
        val teamData = defaultTeamDummyData
        Log.d("prepopulateSQLiteDB","Prepopulation begun")
        for (eventdata in eventData){
            GlobalScope.launch(Dispatchers.IO) {
                repo.insertEventData(eventdata)
            }
        }
        for (matchdata in matchData){
            GlobalScope.launch(Dispatchers.IO) {
                repo.insertMatchData(matchdata)
            }
        }
        for (playerdata in playerData){
            GlobalScope.launch(Dispatchers.IO) {
                repo.insertPlayer(playerdata)
            }
        }
        for (playerstatsdata in playerStatsData){
            GlobalScope.launch(Dispatchers.IO) {
                repo.insertPlayerStats(playerstatsdata)
            }
        }
        for (team in teamData){
            GlobalScope.launch(Dispatchers.IO) {
                repo.insertTeam(team)
            }
        }
        Log.d("prepopulateSQLiteDB","Prepopulation finished")
    }
}


