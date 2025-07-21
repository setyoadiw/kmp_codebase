package com.setyo.mycmpapplication.news.data.repository

import com.setyo.mycmpapplication.core.data.network.ApiResult
import com.setyo.mycmpapplication.core.data.network.NetworkOnlyResource
import com.setyo.mycmpapplication.core.domain.Resource
import com.setyo.mycmpapplication.news.data.remote.dto.NewsHeadlineDto
import com.setyo.mycmpapplication.news.data.remote.mapper.toArticlesDomain
import com.setyo.mycmpapplication.news.data.remote.network.RemoteNewsDataSource
import com.setyo.mycmpapplication.news.domain.model.Articles
import com.setyo.mycmpapplication.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepositoryImp(
    val dataSource: RemoteNewsDataSource
) : NewsRepository {

    override fun getTopHeadlines(): Flow<Resource<List<Articles>>> =
        object : NetworkOnlyResource<List<Articles>, NewsHeadlineDto>() {

            override suspend fun fetchData(): ApiResult<NewsHeadlineDto> {
                return dataSource.getHeadlines()
            }

            override fun mapResult(data: NewsHeadlineDto?): List<Articles> {
                return data?.toArticlesDomain() ?: emptyList()
            }

        }.asFlow()



    override fun getNewsBySearch(query: String): Flow<Resource<Articles>> {
        return flowOf(Resource.Success(Articles()))
    }
}