package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val aboveSeaLevel: Int? = -1,
    val latitude: Double? = (-1).toDouble(),
    val latitudeDirection: String? = "",
    val latitudeRadius: Double? = (-1).toDouble(),
    val longitude: Double? = (-1).toDouble(),
    val longitudeDirection: String? = "",
    val longitudeRadius: Double? = (-1).toDouble()
)