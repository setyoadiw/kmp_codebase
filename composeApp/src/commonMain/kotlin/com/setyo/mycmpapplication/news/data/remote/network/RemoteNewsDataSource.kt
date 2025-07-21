package com.setyo.mycmpapplication.news.data.remote.network

import com.setyo.mycmpapplication.core.data.network.ApiResult
import com.setyo.mycmpapplication.core.domain.Resource
import com.setyo.mycmpapplication.news.data.remote.dto.NewsHeadlineDto

interface RemoteNewsDataSource {

    suspend fun getHeadlines(): ApiResult<NewsHeadlineDto>
}