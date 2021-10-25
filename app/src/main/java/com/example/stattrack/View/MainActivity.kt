package com.example.stattrack.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.R
import com.example.stattrack.View.navbar.BottomNavigationBar
import com.example.stattrack.View.navbar.NavItem
import com.example.stattrack.View.navbar.Navigation
import com.example.stattrack.View.spiller.fragment_spiller
import com.example.stattrack.View.ui.theme.PrimaryBlue
import com.example.stattrack.View.ui.theme.PrimaryWhite

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainScreen()
        }

//        setContentView(R.layout.activity_main)
//
//        val playerFragment = fragment_spiller()
//
//        // Initialize start fragment.
//        /*supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flfragmentTest, playerFragment)
//            commit()
//        }*/
//
//        // Initialize Fragment from button-pressed
//        val btn = findViewById<Button>(R.id.btnFragmentSpiller)
//        btn.setOnClickListener{
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.flfragmentTest, playerFragment)
//                addToBackStack(null)
//                commit()
//            }
//        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController = navController)
    }
}
