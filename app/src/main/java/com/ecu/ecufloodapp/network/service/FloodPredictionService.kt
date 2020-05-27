package com.ecu.ecufloodapp.network.service

import com.ecu.ecufloodapp.model.FloodPrediction
import com.ecu.ecufloodapp.model.WellData
import retrofit2.Retrofit

class FloodPredictionService {
    companion object {
        // Retrofit is not an instance of this service so it needs to be set here
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.10:5000")
            .build()
        val service = retrofit.create(FloodPrediction::class.java)
    }
}