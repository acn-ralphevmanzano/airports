package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Region(
    val regionCode: String,
    val regionName: String
)