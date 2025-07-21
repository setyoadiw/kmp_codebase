package com.setyo.mycmpapplication.news.data.remote.network

import com.setyo.mycmpapplication.core.data.network.ApiResult
import com.setyo.mycmpapplication.core.data.network.networkCallResult
import com.setyo.mycmpapplication.news.data.remote.dto.NewsHeadlineDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteNewsDataSource(
    private val client: HttpClient
) : RemoteNewsDataSource {

    override suspend fun getHeadlines(): ApiResult<NewsHeadlineDto> {
        return networkCallResult(
            execute = {
                client.get("v2/top-headlines") {
                    parameter("country", "us")
                }
            },
        )
    }
}