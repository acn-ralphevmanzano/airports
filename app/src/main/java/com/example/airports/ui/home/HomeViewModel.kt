package com.example.airports.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import com.example.airports.domain.usecase.GetAirportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAirportsUseCase: GetAirportsUseCase) : ViewModel() {

    private val _airportsResult = MutableLiveData<Resource<List<Airport>>>()
    val airportsResult: LiveData<Resource<List<Airport>>> = _airportsResult

    init {
        getAirports()
    }

    fun getAirports() {
        viewModelScope.launch {
            getAirportsUseCase().collect {
                _airportsResult.value = it
            }
        }
    }

}