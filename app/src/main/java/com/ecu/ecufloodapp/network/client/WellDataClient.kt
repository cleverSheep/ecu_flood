package com.ecu.ecufloodapp.network.client

import com.ecu.ecufloodapp.model.WellData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WellDataClient {
    @GET("getWellData/{id}")
    fun getWellData(@Path("id") id: Int): Single<WellData>
}