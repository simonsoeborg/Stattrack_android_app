package com.example.stattrack.model.database

import com.example.stattrack.ext.mapIterable
import com.example.stattrack.model.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository (
    private val database: AppDatabase
) {
    /* ------------------------------------------------------------------ */
    /* Create and update methods  */
    suspend fun insertEventData(eventData: EventData) {
        val dbEventData = eventData.toEntity()
        try {
            database.EventDataDao().insert(dbEventData)
        } catch (ex: Exception) {
            print("Exception @ Repository method insertEventData: " + ex.message)
        }
    }

    suspend fun insertMatchData(matchData: MatchData) {
        val dbMatchData = matchData.toEntity()
        try {
            database.MatchDataDao().insert(dbMatchData)
        } catch (ex: Exception) {
            print("Exception @ Repository method insertMatchData: " + ex.message)
        }
    }

    suspend fun insertPlayer(player: Player) {
        val dbPlayerEntity = player.toEntity()
        try {
            database.PlayerDao().insert(dbPlayerEntity)
        } catch (ex: Exception) {
            print("Exception @ Repository method insertPlayer: " + ex.message)
        }
    }

    suspend fun insertPlayerStats(playerStats: PlayerStats) {
        val dbPlayerStats = playerStats.toEntity()
        try {
            database.PlayerStatsDao().insert(dbPlayerStats)
        } catch (ex: Exception) {
            print("Exception @ Repository method insertPlayerStats: " + ex.message)
        }
    }

    suspend fun insertTeam(team: Team) {
        val dbTeam = team.toEntity()
        try {
            database.TeamDao().insert(dbTeam)
        } catch (ex: Exception) {
            print("Exception @ Repository method insertTeam: " + ex.message)
        }
    }

    /* ------------------------------------------------------------------ */


    /* ------------------------------------------------------------------ */
    /* Read methods */

    // EventData
    fun getEventDataById(eventDataId: Int): Flow<EventData> =
        database.EventDataDao()
            .loadById(eventDataId)
            .map { it?.toModel() ?: EventData(0, " ", 0, " ", "02:59",0) }

    fun getEventDataByPlayerId(playerId: Int): Flow<List<EventData>> =
        database.EventDataDao()
            .loadByPlayerId(playerId = playerId)
            .mapIterable { eventEntity ->
                eventEntity.toModel()   }

    fun getEventDataByMatchId(matchId: Int): Flow<List<EventData>> =
        database.EventDataDao()
            .loadByMatchId(matchId)
            .mapIterable { eventEntity ->
                eventEntity.toModel()   }

    fun getAllEvents(): Flow<List<EventData>> =
        database.EventDataDao()
            .loadAll()
            .mapIterable { eventEntity ->
                eventEntity.toModel() }

    // MatchData
    fun getMatchDataById(matchId: Int): Flow<MatchData> =
        database.MatchDataDao()
            .loadById(matchId)
            .map { it?.toModel() ?: MatchData(0,"null",0,"null","null",0,0) }

    fun getMatchDataByCreatorId(creatorId: String): Flow<List<MatchData>> =
        database.MatchDataDao()
            .loadByCreatorId(creatorId)
            .mapIterable { matchDataEntity ->
                matchDataEntity.toModel() }

    fun getMatchDataByDate(date: String): Flow<List<MatchData>> =
        database.MatchDataDao()
            .loadByMatchDate(date)
            .mapIterable { matchDataEntity ->
                matchDataEntity.toModel() }

    fun getAllMatchData(): Flow<List<MatchData>> =
        database.MatchDataDao()
            .loadAll()
            .mapIterable { matchDataEntity ->
                matchDataEntity.toModel() }

    // Player
    fun getPlayerById(id: Int): Flow<Player> =
        database.PlayerDao()
            .loadById(id)
            .map { it?.toModel() ?: Player(0,"null","null",0,0) }

    fun getPlayerByName(name: String): Flow<Player> =
        database.PlayerDao()
            .loadByName(name)
            .map { it?.toModel() ?: Player(0,"null","null",0,0) }

    fun getAllPlayersFromTeam(teamId: Int): Flow<List<Player>> =
        database.PlayerDao()
            .loadByTeamId(teamId)
            .mapIterable { playerEntity ->
                playerEntity.toModel() }

    fun getAllPlayers(): Flow<List<Player>> =
        database.PlayerDao()
            .loadAll()
            .mapIterable { playerEntity ->
                playerEntity.toModel() }

    // PlayerStats
    fun getPlayerStatsById(playerId: Int): Flow<List<PlayerStats>> =
        database.PlayerStatsDao()
            .loadById(playerId)
            .mapIterable { playerStatsEntity ->
                playerStatsEntity.toModel() }

    fun getAllPlayerStats(): Flow<List<PlayerStats>> =
        database.PlayerStatsDao()
            .loadAll()
            .mapIterable { playerStatsEntity ->
                playerStatsEntity.toModel() }

    // Team
    fun getTeamById(teamId: Int): Flow<Team> =
        database.TeamDao()
            .loadById(teamId)
            .map { it?.toModel() ?: Team(0, "null", "null", "null", "null", "null") }

    fun getTeamByName(name: String): Flow<Team> =
        database.TeamDao()
            .loadByName(name)
            .map { it?.toModel() ?: Team(0, "null", "null", "null", "null", "null") }

    fun getAllTeams(): Flow<List<Team>> =
        database.TeamDao()
            .loadAll()
            .mapIterable { teamEntity ->
                teamEntity.toModel() }

    /* ------------------------------------------------------------------ */




    /* ------------------------------------------------------------------ */
    /* Delete methods */
    suspend fun deletePlayer(playerId: Int){
        database.PlayerDao().delete(playerId)
        database.PlayerStatsDao().deletePlayerStatsForPlayer(playerId)
    }
    /* ------------------------------------------------------------------ */

}