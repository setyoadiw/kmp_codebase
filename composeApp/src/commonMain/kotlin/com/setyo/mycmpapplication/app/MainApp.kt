package com.setyo.mycmpapplication.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.setyo.mycmpapplication.Greeting
import com.setyo.mycmpapplication.core.presentation.theme.MyThemeApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApp(
    viewModel: MainActivityViewModel = koinViewModel<MainActivityViewModel>()
) {
    MyThemeApplication {
         MyAppContent()
     }
}

@Composable
fun MyAppContent() {
    Box(
        modifier = Modifier.fillMaxSize()
            .consumeWindowInsets(WindowInsets.statusBars)
            .consumeWindowInsets(WindowInsets.navigationBars)
            .background(Color.White)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
        ,
    ) {
        AppNavHost()
    }

}