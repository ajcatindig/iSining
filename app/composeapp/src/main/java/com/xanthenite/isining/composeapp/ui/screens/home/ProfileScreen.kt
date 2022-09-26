package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.dialog.ConfirmationDialog
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.main.ExhibitTopBar
import com.xanthenite.isining.composeapp.component.scaffold.main.ProfileTopBar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.main.ProfileViewModel

@Composable
fun ProfileScreen(viewModel : ProfileViewModel, onNavigateToLogin : () -> Unit)
{

    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    var showLogoutConfirmation by remember { mutableStateOf(false) }

    ProfileContent(
        isLoading = state.isLoading ,
        isConnectivityAvailable = state.isConnectivityAvailable,
        onRefresh = { /*TODO*/ },
        onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
        onLogoutClick = { showLogoutConfirmation = true })

    LogoutConfirmation(
            show = showLogoutConfirmation,
            onConfirm = viewModel::logout,
            onDismiss = { showLogoutConfirmation = false })

    val isUserLoggedIn = state.isUserLoggedIn
    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn == false) {
            onNavigateToLogin()
        }
    }

}

@Composable
fun ProfileContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        error : String? = null,
        onRefresh : () -> Unit,
        onToggleTheme : () -> Unit,
        onLogoutClick : () -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ProfileTopBar(
                actions = {
                    ThemeSwitchAction(onToggleTheme)
                })
        },
        content = {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isLoading) ,
                onRefresh = onRefresh ,
                swipeEnabled = isConnectivityAvailable == true)
            {
                Column {
                    if (isConnectivityAvailable != null) {
                        ConnectivityStatus(isConnectivityAvailable)
                    }
                    Button(onClick = { onLogoutClick() }) {
                        Text(text = "Logout")
                    }
                }
            }
        }
    )
}

@Composable
fun LogoutConfirmation(show: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    if (show) {
        ConfirmationDialog(
                title = "Logout?",
                message = "Sure want to logout?",
                onConfirmedYes = onConfirm,
                onConfirmedNo = onDismiss,
                onDismissed = onDismiss)
    }
}