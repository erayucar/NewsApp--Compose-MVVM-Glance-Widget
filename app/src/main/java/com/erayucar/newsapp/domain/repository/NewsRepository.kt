package com.erayucar.newsapp.domain.repository

import com.erayucar.newsapp.data.remote.dto.NewsTopHeadLinesDto

interface NewsRepository {

     suspend fun getNews(category: String, country: String): NewsTopHeadLinesDto
}