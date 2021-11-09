package com.example.airports.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import com.example.airports.domain.model.Status
import com.example.airports.domain.usecase.GetAirportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAirportsUseCase: GetAirportsUseCase) : ViewModel() {

    private val _airportsList = MutableLiveData<Resource<List<Airport>>>()
    val airportsList: LiveData<Resource<List<Airport>>> = _airportsList

    init {
        viewModelScope.launch {
            getAirportsUseCase().collect {
                _airportsList.value = it
            }
        }
    }

}