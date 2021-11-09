package com.example.airports.utils

import com.example.airports.domain.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

suspend fun <T> resultFlow(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): Flow<Resource<T>> {
    return flow {
        try {
            emit(Resource.loading())
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                emit(Resource.success(response.body()))
            } else {
                emit(Resource.error(response.message(), response.code().toString()))
            }
        } catch (t: Throwable) {
            emit(Resource.error("An unexpected error occurred"))
        }
    }.flowOn(dispatcher)
}