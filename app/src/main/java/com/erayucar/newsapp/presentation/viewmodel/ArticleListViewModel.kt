package com.erayucar.newsapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.newsapp.common.Resource
import com.erayucar.newsapp.domain.use_case.NewsTopHeadLinesUseCase
import com.erayucar.newsapp.presentation.ArticleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    val newsTopHeadLinesUseCase: NewsTopHeadLinesUseCase
) : ViewModel() {

    private val _articleState = mutableStateOf(ArticleListState())
    val articleState: State<ArticleListState> = _articleState

    init {
        loadArticles("general", "tr")
    }

    fun loadArticles(category: String, country: String) {

        newsTopHeadLinesUseCase(category, country).onEach {
            when (it) {
                is Resource.Success -> {
                    _articleState.value = ArticleListState(articles = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _articleState.value =
                        ArticleListState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _articleState.value = ArticleListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}