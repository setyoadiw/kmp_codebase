package com.setyo.mycmpapplication.core.data.network

import com.setyo.mycmpapplication.core.domain.ErrorType
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> networkCallResult(
    crossinline execute: suspend () -> HttpResponse
): ApiResult<T> {
    return try {
        val response = execute()
        handleHttpResponse<T>(response)
    } catch (e: Exception) {
        e.printStackTrace()
        handleException(e)
    }
}

suspend inline fun <reified T> handleHttpResponse(response: HttpResponse): ApiResult<T> {
    return when (response.status.value) {
        in 200..299 -> {
            parseBody<T>(response)
        }
        408 -> ApiResult.Failure(ErrorType.Remote.REQUEST_TIMEOUT)
        429 -> ApiResult.Failure(ErrorType.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> ApiResult.Failure(ErrorType.Remote.SERVER)
        else -> ApiResult.Failure(ErrorType.Remote.UNKNOWN)
    }
}

suspend inline fun <reified T> parseBody(response: HttpResponse): ApiResult<T> {
    return try {
        @Suppress("UNCHECKED_CAST")
        val typeInfo = typeInfo<T>()
        val body = response.call.body(typeInfo) as T
        ApiResult.Success(body)
    } catch (e: NoTransformationFoundException) {
        e.printStackTrace()
        ApiResult.Failure(ErrorType.Remote.SERIALIZATION)
    }
}

suspend fun <T> handleException(e: Exception): ApiResult<T> {
    return when (e) {
        is SocketTimeoutException -> ApiResult.Failure(ErrorType.Remote.REQUEST_TIMEOUT)
        is UnresolvedAddressException -> ApiResult.Failure(ErrorType.Remote.NO_INTERNET)
        else -> {
            coroutineContext.ensureActive()
            ApiResult.Failure(
                when (e) {
                    else -> ErrorType.Remote.UNKNOWN
                }
            )
        }
    }
}