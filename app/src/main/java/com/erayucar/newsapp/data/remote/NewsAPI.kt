package com.erayucar.newsapp.data.remote

import com.erayucar.newsapp.common.Constants
import com.erayucar.newsapp.data.remote.dto.NewsTopHeadLinesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsTopHeadLinesDto

}