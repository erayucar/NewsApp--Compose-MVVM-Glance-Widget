package com.erayucar.newsapp.presentation

import com.erayucar.newsapp.domain.model.Article

data class ArticleListState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val error: String = "",
    val search: String = ""
) {
}