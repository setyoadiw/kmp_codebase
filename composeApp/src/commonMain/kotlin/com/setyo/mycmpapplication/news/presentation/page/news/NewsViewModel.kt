package com.setyo.mycmpapplication.news.presentation.page.news

import androidx.lifecycle.viewModelScope
import com.setyo.mycmpapplication.core.domain.Resource
import com.setyo.mycmpapplication.core.presentation.base.BaseViewModel
import com.setyo.mycmpapplication.news.domain.repository.NewsRepository
import com.setyo.mycmpapplication.news.presentation.page.news.NewsEvent.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : BaseViewModel<NewsState, NewsAction, NewsEvent>(NewsState()) {

    override fun onAction(event: NewsAction) {
        when (event) {
            is NewsAction.OnFavoritesClick -> {

            }
            is NewsAction.OnItemClick -> {
                viewModelScope.launch { sendEvent(NavigateToNewsDetail(event.id)) }
            }

            else -> Unit
        }
    }

    override fun onStart() {
        fetchData()
    }

    private fun fetchData() = newsRepository.getTopHeadlines()
        .onEach { res ->
            when(res) {
                is Resource.Loading -> setState { it.copy(isLoading = true) }
                is Resource.Success -> setState { it.copy(isLoading = false, articles = res.data ?: emptyList()) }
                is Resource.Error -> {
                    setState { it.copy(isLoading = false) }
                    sendEvent(ShowError(res.errorMessage ?: "Unknown error"))
                }
            }
        }
        .launchIn(viewModelScope)
}