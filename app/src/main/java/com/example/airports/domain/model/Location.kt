package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val aboveSeaLevel: Int? = -1,
    val latitude: Double? = Double.NaN,
    val latitudeDirection: String? = "",
    val latitudeRadius: Double? = Double.NaN,
    val longitude: Double? = Double.NaN,
    val longitudeDirection: String? = "",
    val longitudeRadius: Double? = Double.NaN
)