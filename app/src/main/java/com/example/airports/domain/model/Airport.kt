package com.example.airports.domain.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class Airport(
    val airportCode: String? = "",
    val airportName: String? = "",
    val city: City = City(),
    val country: Country = Country(),
    val domesticAirport: Boolean = false,
    val eticketableAirport: Boolean = false,
    val internationalAirport: Boolean = false,
    val location: Location = Location(),
    val onlineIndicator: Boolean = false,
    val region: Region = Region(),
    val regionalAirport: Boolean = false
) {
    fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Airport::class.java)
        return jsonAdapter.toJson(this)
    }

    companion object {
        fun fromJson(json: String): Airport? {
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(Airport::class.java)
            return jsonAdapter.fromJson(json)
        }
    }

}