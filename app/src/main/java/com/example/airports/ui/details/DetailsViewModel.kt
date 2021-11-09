package com.example.airports.ui.details

import androidx.lifecycle.ViewModel
import com.example.airports.domain.model.Airport

class DetailsViewModel: ViewModel() {

    var airport: Airport? = null

    fun extractArgs(args: DetailsFragmentArgs) {
        airport = Airport.fromJson(args.airportJson)
    }

}