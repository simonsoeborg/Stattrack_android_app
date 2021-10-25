package com.example.stattrack.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.stattrack.R
import com.example.stattrack.View.spiller.fragment_spiller
import com.example.stattrack.View.navbar.build_navbar as navbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerFragment = fragment_spiller()

        // Initialize start fragment.
        /*supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragmentTest, playerFragment)
            commit()
        }*/

        // Initialize Fragment from button-pressed
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


