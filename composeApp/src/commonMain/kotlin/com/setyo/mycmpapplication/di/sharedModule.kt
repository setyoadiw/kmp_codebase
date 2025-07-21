package com.setyo.mycmpapplication.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.setyo.mycmpapplication.core.data.database.DatabaseFactory
import com.setyo.mycmpapplication.core.data.database.NewsDatabase
import com.setyo.mycmpapplication.core.data.network.HttpClientFactory
import com.setyo.mycmpapplication.news.data.remote.network.KtorRemoteNewsDataSource
import com.setyo.mycmpapplication.news.data.remote.network.RemoteNewsDataSource
import com.setyo.mycmpapplication.news.data.repository.NewsRepositoryImp
import com.setyo.mycmpapplication.news.domain.repository.NewsRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<NewsDatabase>().favoriteNews }

    singleOf(::KtorRemoteNewsDataSource).bind<RemoteNewsDataSource>()
    singleOf(::NewsRepositoryImp).bind<NewsRepository>()
}