package com.example.stattrack.model.database

import retrofit2.http.GET
import retrofit2.http.Path


interface SimpleChartApi {

    @GET("chart?chartInput")
    suspend fun getPlayerChart(@Path("chartInput")chartInput): PlayerChart

}