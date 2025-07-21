package com.setyo.mycmpapplication.core.data.network

import com.setyo.mycmpapplication.core.domain.Resource
import com.setyo.mycmpapplication.core.domain.toLocalizedMessage
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException


abstract class NetworkOnlyResource<ResultType, RequestType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResult = fetchData()) {
            is ApiResult.Success -> {
                val mappedResult = mapResult(apiResult.body)
                emit(Resource.Success(mappedResult))
            }

            is ApiResult.Failure -> {
                emit(Resource.Error(
                    errorType = apiResult.error,
                    errorCode = apiResult.error.name,
                    errorMessage = apiResult.error.toLocalizedMessage()
                ))
            }
        }
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun fetchData(): ApiResult<RequestType>

    protected abstract fun mapResult(data: RequestType?): ResultType
}

