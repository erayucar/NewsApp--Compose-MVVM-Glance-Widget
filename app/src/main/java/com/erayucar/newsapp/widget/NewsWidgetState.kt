package com.erayucar.newsapp.widget

import com.erayucar.newsapp.domain.model.Article

class NewsWidgetState(
    val isLoading: Boolean = false,
    val news: List<Article>? = null,
    val error: String = ""
) {
}