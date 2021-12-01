package com.example.stattrack.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val id : Int,
    val name : String,
    val position : String,
    val yob : Int,
    val teamId : Int
     ): Parcelable


val positions = listOf(
    "Fløjspiller",
    "Bagspiller",
    "Målvogter",
    "Stregspiller",
    "Playmaker"
)

val names = listOf(
    "Albert Antonsen",
    "Bent Bentsen",
    "Carl Carlsberg",
    "Dennis Dingo",
    "Erik Eriksen",
    "Frederik Fredriksen",
    "Gunnar Gunnarsen",
    "Henrik Henriksen",
    "Ib Ibsen",
    "Jan Jansen",
    "Knud Knudsen",
    "Lars Larsen",
)

val defaultDummyPlayerData = listOf(
    Player(1,"Albert Antonsen","Målvogter",1993,1),
    Player(2,"Bent Bentsen","Målvogter",1993,1),
    Player(3,"Carl Carlsberg","Bagspiller",1993,1),
    Player(4,"Dennis Dingo","Bagspiller",1993,1),
    Player(5,"Erik Eriksen","Bagspiller",1993,1),
    Player(6,"Frederik Fredriksen","Bagspiller",1993,1),
    Player(7,"Gunnar Gunnarsen","Stregspiller",1993,1),
    Player(8,"Henrik Henriksen","Stregspiller",1993,1),
    Player(9,"Ib Ibsen","Fløjspiller",1993,1),
    Player(10,"Jan Jansen","Fløjspiller",1993,1),
    Player(11,"Knud Knudsen","Fløjspiller",1993,1),
    Player(12,"Lars Larsen","Fløjspiller",1993,1),


    Player(13,"Albert Antonsen","Målvogter",1993,2),
    Player(14,"Bent Bentsen","Målvogter",1993,2),
    Player(15,"Carl Carlsberg","Bagspiller",1993,2),
    Player(16,"Dennis Dingo","Bagspiller",1993,2),
    Player(17,"Erik Eriksen","Bagspiller",1993,2),
    Player(18,"Frederik Fredriksen","Bagspiller",1993,2),
    Player(19,"Gunnar Gunnarsen","Stregspiller",1993,2),
    Player(20,"Henrik Henriksen","Stregspiller",1993,2),
    Player(21,"Ib Ibsen","Fløjspiller",1993,2),
    Player(22,"Jan Jansen","Fløjspiller",1993,2),
    Player(23,"Knud Knudsen","Fløjspiller",1993,2),
    Player(24,"Lars Larsen","Fløjspiller",1993,2),

    Player(25,"Albert Antonsen","Målvogter",1993,3),
    Player(26,"Bent Bentsen","Målvogter",1993,3),
    Player(27,"Carl Carlsberg","Bagspiller",1993,3),
    Player(28,"Dennis Dingo","Bagspiller",1993,3),
    Player(29,"Erik Eriksen","Bagspiller",1993,3),
    Player(30,"Frederik Fredriksen","Bagspiller",1993,3),
    Player(31,"Gunnar Gunnarsen","Stregspiller",1993,3),
    Player(32,"Henrik Henriksen","Stregspiller",1993,3),
    Player(33,"Ib Ibsen","Fløjspiller",1993,3),
    Player(34,"Jan Jansen","Fløjspiller",1993,3),
    Player(35,"Knud Knudsen","Fløjspiller",1993,3),
    Player(36,"Lars Larsen","Fløjspiller",1993,3),

    Player(37,"Cyrus","Målvogter",1981,4),
    Player(38,"Carsten","Målvogter",1983,4),
    Player(39,"Michael","Bagspiller",1985,4),
    Player(40,"Marianne","Bagspiller",1989,4),
    Player(41,"Cosmin","Bagspiller",1987,4),
    Player(42,"Elias N. Brandt","Bagspiller",1982,4),
    Player(43,"Louise M. Schou","Stregspiller",1980,4),
    Player(44,"Tristan C. Ravn","Stregspiller",1979,4),
    Player(45,"Noah O. Frandsen","Fløjspiller",1993,4),
    Player(46,"Simone P. Mortensen","Fløjspiller",1996,4),
    Player(47,"Julie T. Bruun","Fløjspiller",1991,4),
    Player(48,"Annemette M. Lauridsen","Fløjspiller",1990,4)
)