package com.example.airports.domain.model

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val code: String? = null
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String, code: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}