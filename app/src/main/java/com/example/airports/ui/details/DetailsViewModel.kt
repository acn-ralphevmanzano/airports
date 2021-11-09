package com.example.airports.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airports.domain.model.Airport

class DetailsViewModel: ViewModel() {
    private val _airport = MutableLiveData<Airport>()
    val airport: LiveData<Airport> = _airport

    fun extractArgs(args: DetailsFragmentArgs) {
        _airport.value = Airport.fromJson(args.airportJson)
    }
}