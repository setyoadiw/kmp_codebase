package com.setyo.mycmpapplication.news.presentation.page.news

import com.setyo.mycmpapplication.news.domain.model.Articles

data class NewsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val articles: List<Articles> = emptyList(),
    val selectedTabIndex: Int = 0
) {
    data class NewsItem(
        val id: String,
        val title: String,
        val description: String,
        val imageUrl: String?,
        val publishedAt: String
    )
}