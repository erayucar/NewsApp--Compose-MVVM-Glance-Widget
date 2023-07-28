package com.erayucar.newsapp.domain.use_case

import com.erayucar.newsapp.common.Resource
import com.erayucar.newsapp.domain.model.Article
import com.erayucar.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsTopHeadLinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(category: String, country: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.getNews(category = category, country = country)
            emit(Resource.Success(news.articles))

        } catch (e: IOException) {
           emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}