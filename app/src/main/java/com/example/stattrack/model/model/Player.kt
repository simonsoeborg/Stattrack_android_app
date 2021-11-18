package com.example.stattrack.model.model

data class Player(
    val id : Int,
    val name : String,
    val position : String,
    val yob : Int,
    val teamId : Int
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
    Player(12,"Lars Larsen","Fløjspiller",1993,1)
)