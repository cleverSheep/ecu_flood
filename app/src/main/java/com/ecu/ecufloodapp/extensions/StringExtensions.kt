package com.ecu.ecufloodapp.extensions

fun String.formatWellId(): Int = Character.getNumericValue(toCharArray()[length - 1])