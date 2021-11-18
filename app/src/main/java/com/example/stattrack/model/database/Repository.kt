package com.example.stattrack.model.database

import com.example.stattrack.ext.mapIterable
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository (
    private val database: AppDatabase
) {
    /* For now we have outcommented this State config due to update @Shortcut demo project
       See below:

    // Repositories are usually singletons within a project. Offering progress state through a
    // separate hot stream may result in ambiguous outcome as one's progress may be observed by
    // another.
    data class State(
        val isInProgress: Boolean,
        val error: String? = null
    )

    /* val states can be used in ViewModel to see if Repository is busy or ready
    *  it can only be read from outside of Repository and only set from inside
    *   */
    private val _states = MutableStateFlow(State(isInProgress = false))
    val states = _states.asStateFlow() */

    /* ------------------------------------------------------------------ */
    /* Create and update methods  */
    suspend fun insertEventData(eventData: EventData) {
        val dbEventData = eventData.toEntity()
        try {
            database.EventDataDao().insert(dbEventData)
        } catch (ex: Exception) {
            print("Exception @ Repository" + ex.message)
        }
    }

    suspend fun insertMatchData(matchData: MatchData) {
        val dbMatchData = matchData.toEntity()
        try {
            database.MatchDataDao().insert(dbMatchData)
        } catch (ex: Exception) {
            print("Exception @ Repository" + ex.message)
        }
    }

    suspend fun insertPlayer(player: Player) {
        val dbPlayerEntity = player.toEntity()
        try {
            database.PlayerDao().insert(dbPlayerEntity)
        } catch (ex: Exception) {
            print("Exception @ Repository" + ex.message)
        }
    }

    suspend fun insertPlayerStats(playerStats: PlayerStats) {
        val dbPlayerStats = playerStats.toEntity()
        try {
            database.PlayerStatsDao().insert(dbPlayerStats)
        } catch (ex: Exception) {
            print("Exception @ Repository" + ex.message)
        }
    }

    suspend fun insertTeam(team: Team) {
        val dbTeam = team.toEntity()
        try {
            database.TeamDao().insert(dbTeam)
        } catch (ex: Exception) {
            print("Exception @ Repository" + ex.message)
        }
    }

    /* ------------------------------------------------------------------ */


    /* ------------------------------------------------------------------ */
    /* Read methods */

    // EventData
    fun getEventDataById(eventDataId: Int): Flow<EventData> =
        database.EventDataDao()
            .loadById(eventDataId)
            .map { it?.toModel() ?: EventData(0, " ", 0, " ", 0) }


    fun getEventDataByPlayerId(playerId: Int): Flow<List<EventData>> =
        database.EventDataDao()
            .loadByPlayerId(playerId = playerId)
            .mapIterable { eventEntity ->
                eventEntity.toModel() ?: EventData(0, " ", 0, " ", 0)
            }



    fun getEventDataByMatchId(matchId: Int): Flow<EventData> =
        database.EventDataDao()
            .loadByMatchId(matchId = matchId)
            .map { it?.toModel() ?: EventData(0," ", 0," ",0) }

    /* TODO: Implement me
    fun getAllEvents(): Flow<List<EventData>> {
        database.EventDataDao()
            .loadAll()
            . what ever u want to make it work
    }
     */

    fun getPlayerByName(name: String): Flow<Player> =
        database.PlayerDao()
            .loadByName(name)
            .map { it?.toModel() ?: Player(0, "null", "null", 0, 0) }

    fun getAllPlayers(): List<Player> {
        return defaultDummyPlayerData
    }

    fun getTeamByName(name: String): Flow<Team> =
        database.TeamDao()
            .loadByName(name)
            .map { it?.toModel() ?: Team(0, "null", "null", "null", "null", "null") }

    /*

     Todo: Implement me
    fun getAllTeams(): Flow<List<Team>> {

    }
    */

    /* ------------------------------------------------------------------ */


    /* ------------------------------------------------------------------ */
    /* Delete methods */
    /* TODO:: Currently we dont have any delete methods                   */
    /* ------------------------------------------------------------------ */


    /* ------------------------------------------------------------------ */
    /* Dummy functions used for @Preview in Compose */
    fun getDummyTeams(): List<Team> {
        return defaultTeamDummyData
    }

    fun getDummyPlayers(): List<Player> {
        return defaultDummyPlayerData
    }

    /* ------------------------------------------------------------------ */
}