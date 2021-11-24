package com.example.stattrack.presentation.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SpecificTeamViewModel(private val repository: Repository) : ViewModel()  {


    private val _players = MutableStateFlow<List<Player>>(emptyList())


    val players : StateFlow<List<Player>> = _players

    fun loadAllPlayersFromTeam(teamId: Int) {
            viewModelScope.launch() {
                repository.getAllPlayersFromTeam(teamId).collect{
                    _players.value = it
                }
            }
        }

    fun insertPlayer(player: Player){
        viewModelScope.launch {
            repository.insertPlayer(player)
        }
    }

    }