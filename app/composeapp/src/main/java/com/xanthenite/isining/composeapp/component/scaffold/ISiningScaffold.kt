package com.xanthenite.isining.composeapp.component.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog

/**
 * Compose's wrapped Scaffold for this project
 */
@Composable
fun ISiningScaffold(
        modifier: Modifier = Modifier ,
        topAppBar : @Composable () -> Unit,
        content : @Composable (PaddingValues) -> Unit = {},
        isLoading : Boolean = false,
        error : String? = null)
{
    if (isLoading) {
        LoaderDialog()
    }
    if (error != null) {
        FailureDialog(error)
    }
    Scaffold(
        modifier = modifier,
        topBar = topAppBar,
        content = content)
}