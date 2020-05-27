package com.ecu.ecufloodapp.network.client

import com.ecu.ecufloodapp.model.FloodPrediction
import retrofit2.Call
import retrofit2.http.GET

interface FloodPredictionClient {
    @GET("/getPredictions")
    fun getPredictions(): Call<List<FloodPrediction>>
}