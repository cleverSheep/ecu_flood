package com.ecu.ecufloodapp.util

import com.ecu.ecufloodapp.model.WellData

fun combineLatLng(wellData: WellData): String {
    val latitide = String.format("%.2f", wellData.latitude)
    val longitude = String.format("%.2f", wellData.longitude)
    return "$latitide,$longitude"
}

