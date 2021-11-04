package com.example.stattrack.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.navigation.compose.rememberNavController
import com.example.stattrack.Data.database.Entity.PlayerEntity
import com.example.stattrack.Data.model.Player
import com.example.stattrack.Presentation.navbar.BottomNavigationBar
import com.example.stattrack.Presentation.navbar.Navigation
import com.example.stattrack.Services.ServiceLocator
import kotlinx.coroutines.flow.Flow

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
// Work in progress
suspend fun testTorben(){
    val repo = ServiceLocator.repository
    val daektorben: Player = Player(1,"Dæktorben Langjern","Chefen",1988,56)
    repo.insertPlayer(daektorben)
    print(repo.loadPlayerByName("Dæktorben Langjern").name)
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
