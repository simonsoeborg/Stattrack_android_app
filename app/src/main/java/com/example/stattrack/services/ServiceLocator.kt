package com.example.stattrack.services


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.database.AppDatabase
import com.example.stattrack.presentation.match.MatchViewModel

object ServiceLocator {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val database: AppDatabase by lazy { AppDatabase.build(application) }

    val repository: Repository by lazy {
        Repository(database)
    }

    // Effectively singleton
    private val viewModelFactory by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    MatchViewModel::class.java -> MatchViewModel(repository)
                    else -> throw IllegalArgumentException("Unsupported ViewModel $modelClass")
                } as T
            }
        }
    }


    val ViewModelStoreOwner.matchViewModel: MatchViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

}