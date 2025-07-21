package com.setyo.mycmpapplication.core.domain

sealed class Resource<T>(
    val data: T? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null,
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(
        errorType: ErrorType? = null,
        errorCode: String? = null,
        errorMessage: String? = null,
        data: T? = null
    ) : Resource<T>(data = data, errorCode = errorCode, errorMessage = errorMessage)

}