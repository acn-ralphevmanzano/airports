package com.example.airports.utils

import com.example.airports.domain.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T> resultFlow(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): Flow<Resource<T>> {
    return flow {
        try {
            emit(Resource.loading(null))
            val response = apiCall.invoke()
            emit(Resource.success(response))
        } catch (t: Throwable) {
            emit(Resource.error("An unexpected error occurred"))
        }
    }.flowOn(dispatcher)
}