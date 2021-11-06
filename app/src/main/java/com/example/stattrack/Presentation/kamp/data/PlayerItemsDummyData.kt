package com.example.stattrack.Presentation.kamp.data

sealed class PlayerItems(var name: String, var playerNumber: Int, var playerPosition: String) {
    object DefaultPlayer : PlayerItems("Vælg spiller", 0, "N/A")
    object DefaultPlayer1 : PlayerItems("Albert Antonsen", 1, "Målvogter")
    object DefaultPlayer2 : PlayerItems("Bent Bentsen", 2, "Målvogter")
    object DefaultPlayer3 : PlayerItems("Carl Carlsberg", 3, "Bagspiller")
    object DefaultPlayer4 : PlayerItems("Dennis Dingo", 4, "Bagspiller")
    object DefaultPlayer5 : PlayerItems("Erik Eriksen", 5, "Bagspiller")
    object DefaultPlayer6 : PlayerItems("Frederik Frederiksen", 6, "Bagspiller")
    object DefaultPlayer7 : PlayerItems("Gunnar Gunnarsen", 7, "Stregspiller")
    object DefaultPlayer8 : PlayerItems("Henrik Henriksen", 8, "Stregspiller")
    object DefaultPlayer9 : PlayerItems("Ib Ibsen", 9, "Fløjspiller")
    object DefaultPlayer10 : PlayerItems("Jan Jansen", 10, "Fløjspiller")
    object DefaultPlayer11 : PlayerItems("Knud Knudsen", 11, "Fløjspiller")
    object DefaultPlayer12 : PlayerItems("Lars Larsen", 12, "Fløjspiller")
}