package com.example.stattrack.Presentation.kamp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.Data.database.Repository
import com.example.stattrack.Data.model.Team
import kotlinx.coroutines.launch

class KampViewModel (private val repository: Repository): ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _teams = MutableLiveData<List<Team>>(emptyList())
    val teams: LiveData<List<Team>> = _teams

    init {
        // loadAvailableTeams()
        // loadAvailablePlayers()
    }
    // TODO
    private fun loadAvailableTeams() {
        viewModelScope.launch {
        //_teams.value = repository.fetchAllTeams()
        }
    }
}