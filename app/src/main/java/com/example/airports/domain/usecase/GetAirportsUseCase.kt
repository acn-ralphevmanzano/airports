package com.example.airports.domain.usecase

import com.example.airports.domain.repository.AirportRepo
import javax.inject.Inject

class GetAirportsUseCase @Inject constructor(private val repo: AirportRepo) {
    suspend operator fun invoke() = repo.getAirports()
}