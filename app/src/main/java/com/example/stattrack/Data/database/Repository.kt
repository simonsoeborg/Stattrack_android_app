package com.example.stattrack.Data.database

import com.example.stattrack.Data.database.local.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Repository (
    private val database: AppDatabase

){
    data class State(
        val isInProgress: Boolean,
        val error: String? = null
    )

    private val _states = MutableStateFlow(State(isInProgress = false))
    val states = _states.asStateFlow()
}