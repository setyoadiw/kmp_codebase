package com.setyo.mycmpapplication

import android.app.Application
import com.setyo.mycmpapplication.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}