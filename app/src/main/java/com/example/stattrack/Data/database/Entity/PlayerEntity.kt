package com.example.stattrack.Data.database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asset")
data class PlayerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: Float,
    @ColumnInfo(name = "change_24") val change24Hr: Float,
    @ColumnInfo(name = "volume_24", defaultValue = "0") val volume24Hr: Float,
)

/*fun PlayerEntity.toModel(): Spiller =
    Spiller(id, name, price, volume24Hr, change24Hr)

fun Spiller.toEntity(): PlayerEntity =
    PlayerEntity(id, name, price, change24Hr, volume24Hr)*/
