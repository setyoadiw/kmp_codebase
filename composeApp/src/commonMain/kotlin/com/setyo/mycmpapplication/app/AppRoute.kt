package com.setyo.mycmpapplication.app

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    @Serializable
    data object NewsPage: AppRoute
}