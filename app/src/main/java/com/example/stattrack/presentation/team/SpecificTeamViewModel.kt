package com.example.stattrack.presentation.team

import android.util.Log
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
    private val _allPlayersInDB = MutableStateFlow<List<Player>>(emptyList())


    val players : StateFlow<List<Player>> = _players

    init {
        loadAllPlayers()
    }

    fun loadAllPlayersFromTeam(teamId: Int) {
            viewModelScope.launch {
                repository.getAllPlayersFromTeam(teamId).collect{
                    _players.value = it
                }
            }
        }

    private fun loadAllPlayers() {
        viewModelScope.launch {
            repository.getAllPlayers().collect {
                _allPlayersInDB.value = it
            }
        }
    }

    fun insertPlayer(player: Player) {
        // Fetches the correct id for the new player async via @getNewPlayerIdFromDB and awaits completion
        viewModelScope.launch {
            if (_allPlayersInDB.value.isNotEmpty()) {
                repository.insertPlayer(
                    player.copy(
                        id = _allPlayersInDB.value.size + 1
                    )
                )
            }

            if (_allPlayersInDB.value.isEmpty()){
                repository.insertPlayer(
                    player.copy(
                        id = 0
                    )
                )
            } else Log.d("Error @sTeamVM: ", " - insertPlayer - Error retrieving correct ID for new player")
        }

        // Refresh from DB to update list of players in specificTeamList and also check if insert is correct
        loadAllPlayersFromTeam(player.teamId)
        // Refresh list of players for next insert-player
        loadAllPlayers()
    }

    fun deleteTeam(teamId: Int){
        viewModelScope.launch {
            repository.deleteTeam(teamId)
        }
    }
}