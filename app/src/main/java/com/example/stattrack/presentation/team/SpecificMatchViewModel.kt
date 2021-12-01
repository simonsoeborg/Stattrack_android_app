package com.example.stattrack.presentation.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.model.EventData
import com.example.stattrack.model.model.MatchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SpecificMatchViewModel(private val repository: Repository) : ViewModel()  {


    private val _matchData = MutableStateFlow(
        MatchData(
            id = 0,
            creatorId = "Hold 1",
            creatorTeamId = 0,
            opponent = "Hold 2",
            matchDate = "00/00-0000",
            creatorTeamGoals = 0,
            opponentGoals = 0
        ))
    private val _events = MutableStateFlow<List<EventData>>(emptyList())


    val matchData: StateFlow<MatchData> = _matchData
    val events: StateFlow<List<EventData>> = _events

    fun getMatchDataFromMatchId(matchId: Int){
        viewModelScope.launch {
            repository.getMatchDataById(matchId)
                .collect {
                    _matchData.value = it
                }
        }
    }

    fun getEventDataFromMatchId(matchId: Int){
        viewModelScope.launch {
            repository.getEventDataByMatchId(matchId)
                .collect {
                    _events.value = it
                }
        }
    }

    fun deleteMatch(matchId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMatchData(matchId)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayerStatsWithMatchId(matchId)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEventDataWithMatchId(matchId)
        }
    }


}