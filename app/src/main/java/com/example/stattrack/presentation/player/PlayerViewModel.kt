package com.example.stattrack.presentation.player


import androidx.lifecycle.*
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.match.MatchViewState
import com.example.stattrack.presentation.team.TeamViewState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Optional.empty

/**
 * [PlayerViewModel] takes as parameter a repository to request data
 * that can be exposed by the [PlayerViewState] into [compose_match] flow in order
 * for the view to render the relevant information
 */
class PlayerViewModel(private val repository: Repository) : ViewModel() {

    private val _playerStats = MutableStateFlow<PlayerStats>(PlayerStats(0,"",0,0,0,0,0,0,0,0))

    val playerStats : StateFlow<PlayerStats> = _playerStats

    fun loadAllPlayerStats(playerId: Int) {
        viewModelScope.launch() {
            repository.getPlayerStatsById(playerId).collect{
                _playerStats.value = it
            }
        }
    }

}



