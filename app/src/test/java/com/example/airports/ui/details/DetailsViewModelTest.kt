package com.example.airports.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.airports.domain.model.Airport
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var args: DetailsFragmentArgs

    @Mock
    private lateinit var observer: Observer<Airport>

    private lateinit var viewModel: DetailsViewModel

    @Captor
    private lateinit var captor: ArgumentCaptor<Airport>

    @Before
    fun setup() {
        viewModel = DetailsViewModel()
    }

    @Test
    fun `given args when extract return expected params`() {
        // Given
        val airport = Airport(airportName = "NAIA")
        `when`(args.airportJson).thenReturn(airport.toJson())

        // When
        viewModel.extractArgs(args)

        // Then
        viewModel.airport.observeForever(observer)
        verify(observer).onChanged(captor.capture())
        assertEquals("NAIA", captor.value.airportName)
        verify(observer, times(1)).onChanged(captor.capture())

        // Cleanup
        viewModel.airport.removeObserver(observer)
    }
}