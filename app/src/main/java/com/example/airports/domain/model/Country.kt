package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val countryCode: String,
    val countryName: String
)