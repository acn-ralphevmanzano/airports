package com.example.airports.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import com.example.airports.domain.model.Status
import com.example.airports.domain.repository.AirportRepo
import com.example.airports.domain.usecase.GetAirportsUseCase
import com.example.airports.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var repo: AirportRepo

    @Mock
    private lateinit var getAirportsObserver: Observer<Resource<List<Airport>>>

    @Mock
    private lateinit var airportsList: List<Airport>

    private lateinit var getAirportsUseCase: GetAirportsUseCase

    @Captor
    private lateinit var captor: ArgumentCaptor<Resource<List<Airport>>>

    @Before
    fun setup() {
        getAirportsUseCase = GetAirportsUseCase(repo)
    }

    @Test
    fun `given server response 200 when fetch should return success`() {
        coroutineScope.runBlockingTest {
            // Given
            val airportCode = "abc"
            val flow = flow {
                emit(Resource.loading())
                kotlinx.coroutines.delay(10)
                emit(Resource.success(airportsList))
            }

            `when`(getAirportsUseCase.invoke()).thenReturn(flow)
            `when`(airportsList[0]).thenReturn(Airport(airportCode))

            // When
            val viewModel = HomeViewModel(getAirportsUseCase)
            viewModel.airportsResult.observeForever(getAirportsObserver)

            // Then
            /** Loading **/
            verify(getAirportsObserver).onChanged(captor.capture())
            assertEquals(true, captor.value.status == Status.LOADING)

            coroutineScope.advanceTimeBy(10)

            /** Response **/
            verify(getAirportsObserver, times(2))
                .onChanged(captor.capture())
            verify(repo).getAirports()
            assertEquals("abc", captor.value.data?.get(0)?.airportCode.orEmpty())

            // Cleanup
            viewModel.airportsResult.removeObserver(getAirportsObserver)
        }
    }

    @Test
    fun `given server error response when fetch should return error`() {
        coroutineScope.runBlockingTest {
            // Given
            val errorMessage = "An unexpected error occurred"
            val flow = flow {
                emit(Resource.loading())
                kotlinx.coroutines.delay(10)
                emit(Resource.error(errorMessage, null, null))
            }

            `when`(getAirportsUseCase.invoke()).thenReturn(flow)

            // When
            val viewModel = HomeViewModel(getAirportsUseCase)
            viewModel.airportsResult.observeForever(getAirportsObserver)

            // Then
            /** Loading **/
            verify(getAirportsObserver).onChanged(captor.capture())
            assertEquals(true, captor.value.status == Status.LOADING)

            coroutineScope.advanceTimeBy(10)

            /** Error **/
            verify(getAirportsObserver, times(2))
                .onChanged(captor.capture())
            verify(repo).getAirports()
            assertEquals(errorMessage, captor.value.message.orEmpty())

            // Cleanup
            viewModel.airportsResult.removeObserver(getAirportsObserver)
        }
    }
}