package com.example.stattrack.View.spiller

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.stattrack.R
import com.example.stattrack.View.navbar.build_navbar


class fragment_spiller : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'

//        val view = inflater.inflate(R.layout.fragment_spiller, container, false)

        val view = ComposeView(requireContext()).apply{
            setContent {
                build_navbar()
            }
        }

        return view
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerInfoDenne = fragment_spiller_data()
        // val playerInfoALle = fragment_spiller_data_alle()

        // Hard Coded spillers Indledende info - Skal hentes fra database.
        view.findViewById<TextView>(R.id.tw_spiller_navn).text = "Simon Fridolf"
        view.findViewById<TextView>(R.id.tw_spiller_position).text = "Højre Back"
        view.findViewById<TextView>(R.id.tw_spiller_aargang).text = "98'ere"
        view.findViewById<TextView>(R.id.tw_spiller_klub).text = "LyngbySeniors"


        var btnAll = view.findViewById<Button>(R.id.btn_spiller_alleSæsoner)
        var btnThis = view.findViewById<Button>(R.id.btn_spiller_denneSæson)

        // Initialiseret starter data (Denne sæson).
        btnThis.setBackgroundColor(Color.parseColor("#2196F3"))
        btnThis.isEnabled = false
        childFragmentManager.beginTransaction().apply {
            replace(R.id.spillerInfo, playerInfoDenne)
            commit()
        }

        // listeners ansvarlig for data og knapper
        btnAll.setOnClickListener {
            btnAll.setBackgroundColor(Color.parseColor("#2196F3"))
            btnAll.isEnabled = false
            btnThis.setBackgroundColor(Color.BLACK)
            btnThis.isEnabled = true

           *//* childFragmentManager.beginTransaction().apply {
                replace(R.id.spillerInfo, )
                commit()
            }*//*
        }

        btnThis.setOnClickListener {
            btnThis.setBackgroundColor(Color.parseColor("#2196F3"))
            btnThis.isEnabled = false
            btnAll.setBackgroundColor(Color.BLACK)
            btnAll.isEnabled = true

            childFragmentManager.beginTransaction().apply {
                replace(R.id.spillerInfo, playerInfoDenne)
                commit()
            }
        }
    }*/
}