package com.erayucar.newsapp.data.remote.dto

import com.erayucar.newsapp.domain.model.Article

data class NewsTopHeadLinesDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

