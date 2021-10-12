package com.example.stattrack.View

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.blue
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.stattrack.R
import androidx.fragment.app.FragmentActivity
import com.example.stattrack.Hold


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_spiller.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_spiller : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'

        return inflater.inflate(R.layout.fragment_spiller, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_spiller.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_spiller().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)


                }

            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerInfoDenne = fragment_spiller_data_denne()
        val playerInfoALle = fragment_spiller_data_alle()

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

            childFragmentManager.beginTransaction().apply {
                replace(R.id.spillerInfo, playerInfoALle)
                commit()
            }
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
    }
}