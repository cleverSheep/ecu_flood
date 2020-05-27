package com.ecu.ecufloodapp.model

data class WellData(
    val elevation: Double,
    val id: Int,
    val landcover: Int,
    val latitude: Double,
    val longitude: Double,
    val precipitation: Int,
    val underground_water: Double
)