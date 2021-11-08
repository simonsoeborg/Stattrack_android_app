package com.example.stattrack.Services

import android.app.Application
import com.example.stattrack.Data.database.Repository
import com.example.stattrack.Data.database.local.AppDatabase

object ServiceLocator {
    private lateinit var applicationContext: Application

    fun init(applicationContext: Application) {
        this.applicationContext = applicationContext
    }

    val database: AppDatabase by lazy { AppDatabase.build(applicationContext) }

    val repository: Repository by lazy {
        Repository(database)
    }

}