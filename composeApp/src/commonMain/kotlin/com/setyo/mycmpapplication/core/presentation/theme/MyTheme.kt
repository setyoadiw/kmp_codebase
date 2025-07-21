package com.setyo.mycmpapplication.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

var lightColorTheme = lightColorScheme(
    primary = AppColors.Primary,
    background = AppColors.Background,
)

@Composable
fun MyThemeApplication(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorTheme,
        content = content
    )
}