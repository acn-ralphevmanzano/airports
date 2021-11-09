package com.example.airports.utils

import android.util.Log
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
            emit(Resource.loading())
            val response = apiCall.invoke()
            emit(Resource.success(response))
        } catch (t: Throwable) {
            Log.e("NetworkHelper", t.message.orEmpty())
            emit(Resource.error("An unexpected error occurred"))
        }
    }.flowOn(dispatcher)
}