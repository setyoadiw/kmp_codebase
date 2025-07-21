package com.setyo.mycmpapplication.news.presentation.page.news

sealed interface NewsAction {
    data object OnBackClick : NewsAction
    data object OnFavoritesClick : NewsAction
    data class OnItemClick(val id: String) : NewsAction

}
sealed interface NewsEvent {
    data class ShowError(val message: String): NewsEvent
    data class NavigateToNewsDetail(val newsId: String): NewsEvent
}