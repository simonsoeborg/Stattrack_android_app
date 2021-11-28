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

val divisions = listOf(
    "Håndboldligaen",
    "1. Div",
    "2. Div",
    "3. Div",
    "Kvalifikationsrækken",
    "Jyllandsserien",
    "Fynsserien",
    "Serie 1",
    "Serie 2",
    "Serie 3",
    "Serie 4",
    "Niveaustævne",
    "Turnering"
)

val defaultTeamDummyData = listOf(
    Team(1,"HC Midtjylland","HC Midtjylland","Kasper","1978","1. Division"),
    Team(2,"Ajax København","Ajax Håndbold","Dennis","1956","Top-top-proffer"),
    Team(3,"TM Tønder","Tønder Herrehåndbold","Jørgen","1964","Top-top-proffer"),
    Team(4,"Nørrebronx Håndbold","Nørrebronx Driblers","Ole","1989","Top-top-proffer"),
    Team(5,"Elitesport Vendsyssel","Vendsyssel Håndboldklub","Henning","2003","Top-top-proffer"),
    Team(6,"Grindsted GIF Håndbold","Grindsted Idrætsklub","Christian","1853","Top-top-proffer")
)