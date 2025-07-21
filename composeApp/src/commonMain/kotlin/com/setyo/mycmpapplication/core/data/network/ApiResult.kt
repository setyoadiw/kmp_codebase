package com.setyo.mycmpapplication.core.data.network

import com.setyo.mycmpapplication.core.domain.ErrorType

sealed class ApiResult<T>(
    val body: T? = null,
) {
    class Success<T>(body: T?) : ApiResult<T>(body)
    data class Failure<T>(val error: ErrorType.Remote) : ApiResult<T>()
}