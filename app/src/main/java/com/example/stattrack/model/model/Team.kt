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
    Team(1,"Demo-Hold 1","Demo-Hold 1","Kasper","1978","1. Div"),
    Team(2,"Demo-Hold 2","Demo-Hold 2","Dennis","1956","Håndboldligaen"),
    Team(3,"DTU Handball","Dtu Handball","Jørgen","1964","2. Div"),
    Team(4,"Shortcut Athletics","Shortcut Athletics","Cyrus","1981","1. Div")
    //Team(5,"Elitesport Vendsyssel","Vendsyssel Håndboldklub","Henning","2003","Top-top-proffer"),
    //Team(6,"Grindsted GIF Håndbold","Grindsted Idrætsklub","Christian","1853","Top-top-proffer")
)