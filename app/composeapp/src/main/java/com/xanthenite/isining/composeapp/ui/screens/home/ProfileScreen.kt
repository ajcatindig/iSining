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
import com.xanthenite.isining.composeapp.component.cards.profile.ProfileCard
import com.xanthenite.isining.composeapp.component.dialog.ConfirmationDialog
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.main.ExhibitTopBar
import com.xanthenite.isining.composeapp.component.scaffold.main.ProfileTopBar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.view.viewmodel.main.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel : ProfileViewModel,
    onNavigateToLogin : () -> Unit,
    onChangePasswordClick : () -> Unit,
    onAboutAppCLick : () -> Unit,
    onManageProfileClick : () -> Unit,
    onTransactionClick : () -> Unit)
{

    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    var showLogoutConfirmation by remember { mutableStateOf(false) }

    ProfileContent(
        isLoading = state.isLoading ,
        isConnectivityAvailable = state.isConnectivityAvailable,
        onRefresh = viewModel::getCurrentUser,
        onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
        onLogoutClick = { showLogoutConfirmation = true },
        data = state.data,
        onChangePasswordClick = onChangePasswordClick,
        onAboutAppCLick = onAboutAppCLick,
        onManageProfileClick = onManageProfileClick,
        onTransactionClick = onTransactionClick)

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
        data : User,
        error : String? = null,
        onRefresh : () -> Unit,
        onToggleTheme : () -> Unit,
        onLogoutClick : () -> Unit,
        onChangePasswordClick : () -> Unit,
        onAboutAppCLick : () -> Unit,
        onManageProfileClick : () -> Unit,
        onTransactionClick : () -> Unit)
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
                    ProfileCard(
                            data = data,
                            onLogoutClick = { onLogoutClick() } ,
                            onChangePasswordClick = { onChangePasswordClick() } ,
                            onAboutAppClick = { onAboutAppCLick() } ,
                            onTransactionClick = { onTransactionClick() },
                            onManageProfileClick = { onManageProfileClick() })
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
                message = "Are you sure you want to logout?",
                onConfirmedYes = onConfirm,
                onConfirmedNo = onDismiss,
                onDismissed = onDismiss)
    }
}