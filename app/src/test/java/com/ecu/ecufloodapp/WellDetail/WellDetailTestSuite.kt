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

    @Test
    fun seperateLatLng_ReturnListList() {
        val latlng_combo = "34.65,-77.05"
        val latlng_list = latlng_combo.separateLatLng()
        assertEquals(latlng_list[0], 34.65, 0.0)
        assertEquals(latlng_list[1], -77.05, 0.0)
    }

}

/*private fun seperateLatLng(latlngCombo: String): List<Double> {
    val string_list = latlngCombo.split(",")
    val lat = string_list[0].toDouble()
    val long = string_list[1].toDouble()
    return listOf(lat, long)
}*/

private fun combineLatLng(lat: Double, long: Double): String {
    val latitide = String.format("%.2f", lat)
    val longitude = String.format("%.2f", long)
    return "$latitide,$longitude"
}

private fun String.formatWellId(): Int = Character.getNumericValue(toCharArray()[length - 1])

private fun String.separateLatLng(): List<Double> = listOf(split(",")[0].toDouble(), split(",")[1].toDouble())
