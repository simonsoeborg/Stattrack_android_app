package com.example.stattrack.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.ContentView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.stattrack.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerFragment = fragment_spiller()

        // Initialize start fragment.
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flfragmentTest, playerFragment)
//            commit()
//        }


        // Initialize Fragment from button
        val btn = findViewById<Button>(R.id.btnFragmentSpiller)
        btn.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragmentTest, playerFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}


