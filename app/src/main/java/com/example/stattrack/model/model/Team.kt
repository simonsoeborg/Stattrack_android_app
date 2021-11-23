package com.example.stattrack.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val teamId : Int,
    val name : String,
    val clubName : String,
    // Skal nedenstående stadig bruges?
    val creatorId : String,
    val teamUYear : String,
    val division : String
) : Parcelable

val defaultTeamDummyData = listOf(
    Team(1,"Jylland","HC Midtjylland","Kasper","1978","1. Division"),
    Team(2,"Grindsted","Grindsted GIF Håndbold","Dennis","1956","Top-top-proffer"),
    Team(3,"Hørsholm","Hørsholm Herrehåndbold","Jørgen","1964","Top-top-proffer"),
    Team(4,"Nørrebronx","Nørrebronx Driblers","Ole","1989","Top-top-proffer"),
    Team(5,"Vejle","Vejle Lions","Henning","2003","Top-top-proffer"),
    Team(6,"Odense","Odense all-stars","Christian","1853","Top-top-proffer")
)