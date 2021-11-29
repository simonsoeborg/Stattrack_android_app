package com.example.stattrack.model.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlayerEntity::class, TeamEntity::class, PlayerStatsEntity::class, MatchDataEntity::class, EventDataEntity::class],
    version = 1,
    exportSchema = true,
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun PlayerDao(): PlayerDao
    abstract fun TeamDao(): TeamDao
    abstract fun PlayerStatsDao(): PlayerStatsDao
    abstract fun MatchDataDao(): MatchDataDao
    abstract fun EventDataDao(): EventDataDao

    companion object {
        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "stattrack-db")
                //.fallbackToDestructiveMigration() //TODO: Remove this when done with the app - this clears the db-version caches and sets the db back to version 1 (to avoid migration)
                .build()
        }
    }
}