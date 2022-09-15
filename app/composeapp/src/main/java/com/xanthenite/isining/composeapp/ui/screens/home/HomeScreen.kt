package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel : HomeViewModel, onNavigateToLogin: () -> Unit)
{
    val state by viewModel.collectState()
    
    Surface {
        Button(onClick = viewModel::logout) {
            Text(text = "Logout", color = Color.White)
        }
    }

    val isUserLoggedIn = state.isUserLoggedIn
    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn == false) {
            onNavigateToLogin()
        }
    }
}