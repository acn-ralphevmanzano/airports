package com.example.airports.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airports.data.api.ApiService
import com.example.airports.di.IoDispatcher
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import com.example.airports.domain.model.Status
import com.example.airports.utils.MainCoroutineScopeRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import okhttp3.internal.wait
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AirportRepoImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var responseBody: ResponseBody

    @Test
    fun `when getAirports is called, should call apiService`() {
        coroutineScope.runBlockingTest {
            // Given
            val airportRepoImpl = AirportRepoImpl(apiService, coroutineScope.dispatcher)
            val airportCode = "abc"
            val list = mutableListOf<Airport>()
            list.add(Airport(airportCode))
            val response = Response.success(list.toList())
            `when`(apiService.getAirports()).thenReturn(response)

            // When
            val result = airportRepoImpl.getAirports().toList()

            // Then
            verify(apiService).getAirports()
            assertEquals(result[0].status, Status.LOADING)
            assertEquals(result[1].status, Status.SUCCESS)
            assertEquals(result[1].data?.get(0)?.airportCode, "abc")
        }
    }

    @Test
    fun `when apiService fails, should return status ERROR`() {
        coroutineScope.runBlockingTest {
            // Given
            val airportRepoImpl = AirportRepoImpl(apiService, coroutineScope.dispatcher)
            val response = Response.error<List<Airport>>(404, responseBody)
            `when`(apiService.getAirports()).thenReturn(response)

            // When
            val result = airportRepoImpl.getAirports().toList()

            // Then
            verify(apiService).getAirports()
            assertEquals(result[0].status, Status.LOADING)
            assertEquals(result[1].status, Status.ERROR)
            assertEquals(result[1].code, "404")
        }
    }

    @Test
    fun `when apiService unexpected error, should return status ERROR`() {
        coroutineScope.runBlockingTest {
            // Given
            val airportRepoImpl = AirportRepoImpl(apiService, coroutineScope.dispatcher)
            val exception = MockitoException("")
            `when`(apiService.getAirports()).thenThrow(exception)

            // When
            val result = airportRepoImpl.getAirports().toList()

            // Then
            verify(apiService).getAirports()
            assertEquals(result[0].status, Status.LOADING)
            assertEquals(result[1].status, Status.ERROR)
            assertEquals(result[1].message, "An unexpected error occurred")
        }
    }
}