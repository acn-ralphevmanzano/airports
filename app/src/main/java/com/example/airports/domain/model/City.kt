package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    val cityCode: String,
    val cityName: String,
    val timeZoneName: String
)