package com.ecu.ecufloodapp.extensions

fun String.formatWellId(): Int = Character.getNumericValue(toCharArray()[length - 1])

fun String.separateLatLng(): List<Double> =
    listOf(split(",")[0].toDouble(), split(",")[1].toDouble())