package com.setyo.mycmpapplication.di

import com.setyo.mycmpapplication.app.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::MainActivityViewModel)
}