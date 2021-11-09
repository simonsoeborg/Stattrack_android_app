package com.example.stattrack.Services

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.example.stattrack.Data.database.Repository
import com.example.stattrack.Data.database.local.AppDatabase
import com.example.stattrack.Presentation.kamp.KampViewModel

object ServiceLocator {
    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val database: AppDatabase by lazy { AppDatabase.build(application) }

    val Repository: Repository by lazy {
        Repository(database)
    }

    // Effectively singleton
    private val viewModelFactory by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    KampViewModel::class -> KampViewModel(Repository)
                    else -> throw IllegalArgumentException("Unsupported ViewModel $modelClass")
                } as T
            }
        }
    }


val ViewModelStoreOwner.KampViewModel: KampViewModel
    get() = ViewModelProvider(this, viewModelFactory).get()

}