package com.setyo.mycmpapplication.news.domain.model


data class Articles(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
)

data class Source(
    val name: String? = null,
    val id: String? = null
)