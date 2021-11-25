package com.example.stattrack.presentation.player


import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [PlayerViewModel] takes as parameter a repository to request data
 * that can be exposed into [compose_player] in order
 * for the view to render the relevant information
 */
class PlayerViewModel(private val repository: Repository) : ViewModel() {

    private val _playerStats = MutableStateFlow<PlayerStats>(PlayerStats(0,"",0,0,0,0,0,0,0,0))

    val playerStats : StateFlow<PlayerStats> = _playerStats

    fun loadPlayersStats(playerId: Int) {
        viewModelScope.launch() {
            repository.getPlayerStatsById(playerId).collect{
                _playerStats.value = it
            }
        }
    }

}



