package com.setyo.mycmpapplication.di

import com.setyo.mycmpapplication.core.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory() }
    }