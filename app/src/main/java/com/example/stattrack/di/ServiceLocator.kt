package com.example.stattrack.di


import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.example.stattrack.model.database.Repository
import com.example.stattrack.model.database.AppDatabase
import com.example.stattrack.model.model.*
import com.example.stattrack.presentation.team.TeamViewModel
import com.example.stattrack.presentation.match.MatchViewModel
import com.example.stattrack.presentation.player.PlayerViewModel
import com.example.stattrack.presentation.team.SpecificMatchViewModel
import com.example.stattrack.presentation.team.SpecificTeamViewModel
import com.squareup.picasso.Cache
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object ServiceLocator {


    lateinit var application: Application

    fun init(application: Application) {
        ServiceLocator.application = application
    }

    private val database: AppDatabase by lazy { AppDatabase.build(application) }

    private val repository: Repository by lazy { Repository(database) }

    val picasso: Picasso by lazy {Picasso.Builder(application).executor(Executors.newSingleThreadExecutor())
        .memoryCache(Cache.NONE).indicatorsEnabled(true).build()}





    private val viewModelFactory by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    MatchViewModel::class.java -> MatchViewModel(repository)
                    TeamViewModel::class.java -> TeamViewModel(repository)
                    SpecificTeamViewModel::class.java -> SpecificTeamViewModel(repository)
                    SpecificMatchViewModel::class.java -> SpecificMatchViewModel(repository)
                    PlayerViewModel::class.java -> PlayerViewModel(repository)

                    else -> throw IllegalArgumentException("Unsupported ViewModel $modelClass")
                } as T
            }
        }
    }
    val ViewModelStoreOwner.specificTeamViewModel: SpecificTeamViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

    val ViewModelStoreOwner.specificMatchViewModel: SpecificMatchViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

    val ViewModelStoreOwner.matchViewModel: MatchViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

    val ViewModelStoreOwner.teamViewModel: TeamViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

    val ViewModelStoreOwner.playerViewModel: PlayerViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()


    /* Fill SQLite with dummydata for development purposes */
    fun prepopulateSQLiteDB() {
        GlobalScope.launch() {
            val repo = repository
            val eventData = defaultDummyEventData
            val matchData = defaultDummyMatchData
            val playerStatsData = defaultDummyPlayerStatsData
            val teamData = defaultTeamDummyData
            val playerData = defaultDummyPlayerData
            Log.d("prepopulateSQLiteDB", "Prepopulation begun")
            for (eventdata in eventData) {
                GlobalScope.launch() {
                    repo.insertEventData(eventdata)
                }
            }
            for (matchdata in matchData) {
                GlobalScope.launch() {
                    repo.insertMatchData(matchdata)
                }
            }
            for (playerstatsdata in playerStatsData) {
                GlobalScope.launch() {
                    repo.insertPlayerStats(playerstatsdata)
                }
            }
            for (team in teamData) {
                GlobalScope.launch() {
                    repo.insertTeam(team)
                }
            }
            for (player in playerData) {
                GlobalScope.launch() {
                    repo.insertPlayer(player)
                }
            }
            Log.d("prepopulateSQLiteDB", "Prepopulation finished")
        }
    }
/*
    // Effectively singleton
    private val simpleChartApi: SimpleChartApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://quickchart.io/")
            .addConverterFactory(MoshiConverterFactory.create())

            .build()
            .create()
    }
*/




}

