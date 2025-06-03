package com.setyo.mycmpapplication

import androidx.compose.ui.window.ComposeUIViewController
import com.setyo.mycmpapplication.app.MainApp
import com.setyo.mycmpapplication.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    MainApp()
}