package com.setyo.mycmpapplication.news.domain.repository

import com.setyo.mycmpapplication.core.domain.Resource
import com.setyo.mycmpapplication.news.domain.model.Articles
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(): Flow<Resource<List<Articles>>>
    fun getNewsBySearch(query: String): Flow<Resource<Articles>>
}