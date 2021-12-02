package com.example.stattrack.presentation.player


import androidx.lifecycle.*
import com.example.stattrack.di.ServiceLocator
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.database.SimpleChartApi
import com.example.stattrack.model.model.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [PlayerViewModel] takes as parameter a repository to request data
 * that can be exposed into [compose_player] in order
 * for the view to render the relevant information
 */
class PlayerViewModel(private val repository: Repository) : ViewModel() {


    private val _playerStats = MutableStateFlow<List<PlayerStats>>(listOf(
        PlayerStats(0," ",0,0,0,0,0,0,0,0)
    ))

    val playerStats : StateFlow<List<PlayerStats>> = _playerStats

    fun loadPlayersStats(playerId: Int) {
        viewModelScope.launch() {
            repository.getPlayerStatsById(playerId).collect{
                _playerStats.value = it
            }
        }
    }


    fun loadPlayerStatPic() {

        while (_playerStats.value[0].playerId != 0) {

            viewModelScope.launch(Dispatchers.IO){
                ServiceLocator.picasso.load("http://i.imgur.com/DvpvklR.png%22").into(imageView))

            }

        }
    }


    private val _combinedPlayerStats =  MutableStateFlow<PlayerStats>(PlayerStats(999,"",0,0,0,0,0,0,0,999))
    private  val  _gamesTotal = MutableStateFlow<Int>(0)

    val combinedPlayerStats : StateFlow<PlayerStats> = _combinedPlayerStats
    val gamesTotal : StateFlow<Int> = _gamesTotal

    fun combineStats(playerStatsPerGame: List<PlayerStats>){

        var tempAttempts = 0
        var tempGoals = 0
        var tempKeeperSaves = 0
        var tempAssists = 0
        var tempMin2 = 0
        var tempYellowCards = 0
        var tempRedCards = 0
        var tempGames = 0

        for (PlayerStats in playerStatsPerGame){
            tempGames++
            tempAttempts += PlayerStats.assists
            tempGoals += PlayerStats.goals
            tempKeeperSaves += PlayerStats.keeperSaves
            tempAssists += PlayerStats.assists
            tempMin2 += PlayerStats.mins2
            tempYellowCards += PlayerStats.yellowCards
            tempRedCards += PlayerStats.redCards
        }
        _combinedPlayerStats.value = PlayerStats(999,"",tempAttempts,tempGoals, tempKeeperSaves, tempAssists, tempMin2, tempYellowCards, tempRedCards,999)
        _gamesTotal.value = tempGames
    }

    fun deletePlayer(playerId: Int){
        viewModelScope.launch {
            repository.deletePlayer(playerId)
        }
    }
}



