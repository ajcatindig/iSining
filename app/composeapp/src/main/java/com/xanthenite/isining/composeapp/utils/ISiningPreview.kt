package com.xanthenite.isining.composeapp.utils

import androidx.compose.runtime.Composable
import com.xanthenite.isining.composeapp.ui.theme.ISiningTheme

@Composable
fun ISiningPreview(content : @Composable () -> Unit) {
    ISiningTheme(content = content)
}