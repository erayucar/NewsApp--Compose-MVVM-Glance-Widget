package com.erayucar.newsapp.data.repository

import com.erayucar.newsapp.data.remote.NewsAPI
import com.erayucar.newsapp.data.remote.dto.NewsTopHeadLinesDto
import com.erayucar.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val api: NewsAPI
): NewsRepository {
    override suspend fun getNews(category: String, country: String): NewsTopHeadLinesDto {
        return api.getNews(category = category, country = country)
    }

}