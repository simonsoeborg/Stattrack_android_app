package com.example.stattrack.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class fragment_spiller_data() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply{
            setContent {
                // Todo Hent aktuelt data fra database (brug viewmodels) + lav det således at nedenstående metode bruges af bådes  denne sæson og alle sæson-knapperne.
                FragmentSpillerData(4, 1, 2, 4)
            }
        }
    }
}