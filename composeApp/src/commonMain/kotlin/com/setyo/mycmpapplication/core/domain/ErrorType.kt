package com.setyo.mycmpapplication.core.domain

import my_cmp_application.composeapp.generated.resources.Res
import my_cmp_application.composeapp.generated.resources.*

sealed interface ErrorType {

    data class Local(
        val errorCode: String? = null,
        val errorMessage: String? = null
    ) : ErrorType

    enum class Remote : ErrorType{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }
}

fun ErrorType.Remote.toLocalizedMessage(): String {
    return when (this) {
        ErrorType.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        ErrorType.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        ErrorType.Remote.NO_INTERNET -> Res.string.error_no_internet
        ErrorType.Remote.SERVER -> Res.string.error_unknown
        ErrorType.Remote.SERIALIZATION -> Res.string.error_serialization
        ErrorType.Remote.UNKNOWN -> Res.string.error_unknown
    }.toString()
}