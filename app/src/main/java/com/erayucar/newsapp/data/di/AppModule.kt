package com.erayucar.newsapp.data.di

import com.erayucar.newsapp.common.Constants
import com.erayucar.newsapp.data.remote.NewsAPI
import com.erayucar.newsapp.data.repository.NewsRepoImpl
import com.erayucar.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsAPI): NewsRepository {
        return NewsRepoImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideNewsAPI(): NewsAPI {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsAPI::class.java)
    }


}