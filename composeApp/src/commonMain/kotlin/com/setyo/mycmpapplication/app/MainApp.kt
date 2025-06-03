package com.setyo.mycmpapplication.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.setyo.mycmpapplication.Greeting
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApp(
    viewModel: MainActivityViewModel = koinViewModel<MainActivityViewModel>()
) {
     MaterialTheme {
         MyAppContent()
     }
}

@Composable
fun MyAppContent() {
    Scaffold(
        modifier = Modifier
            .consumeWindowInsets(WindowInsets.systemBars)
            .consumeWindowInsets(WindowInsets.navigationBars)
            .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
        ,
    ) {
        // Content of your app goes here
        // You can add your navigation, screens, etc.
        // For example:
         Text(Greeting().greet())
    }

}