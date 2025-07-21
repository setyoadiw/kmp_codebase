package com.setyo.mycmpapplication.news.data.remote.mapper

import com.setyo.mycmpapplication.news.data.remote.dto.NewsHeadlineDto
import com.setyo.mycmpapplication.news.domain.model.Articles
import com.setyo.mycmpapplication.news.domain.model.Source


fun NewsHeadlineDto.toArticlesDomain(): List<Articles> {
    return articles.map {
        Articles(
            publishedAt = it.publishedAt,
            author = it.author,
            urlToImage = it.urlToImage,
            description = it.description,
            source = it.source?.let { src ->
                Source(name = src.name, id = src.id)
            },
            title = it.title,
            url = it.url,
            content = it.content
        )
    }
}
