package com.ecu.ecufloodapp.WellDetail

import org.junit.Test

import org.junit.Assert.*

class WellDetailTestSuite {

    @Test
    fun combineLatLnt_ReturnCorrectCombo() {
        val lat = 34.6515731
        val long = -77.0541277
        val combo = combineLatLng(lat, long)
        assertEquals(combo, "34.65,-77.05")
    }

    @Test
    fun formatWellID_ReturnLastCharacter() {
        val well_id = "OBB 01"
        val formatted_id = well_id.formatWellId()
        assertEquals(formatted_id, 1)
    }

    private fun combineLatLng(lat: Double, long: Double): String {
        val latitide = String.format("%.2f", lat)
        val longitude = String.format("%.2f", long)
        return "$latitide,$longitude"
    }
}

private fun String.formatWellId(): Int = Character.getNumericValue(toCharArray()[length - 1])
