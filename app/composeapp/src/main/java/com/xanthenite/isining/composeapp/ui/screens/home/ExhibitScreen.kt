package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.list.exhibit.ExhibitList
import com.xanthenite.isining.composeapp.component.scaffold.main.ExhibitTopBar
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.view.viewmodel.main.ExhibitViewModel

@Composable
fun ExhibitScreen(
        viewModel : ExhibitViewModel,
        onNavigateToExhibitDetail: (Int) -> Unit)
{
    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    ExhibitContent(
        isLoading = state.isLoading ,
        isConnectivityAvailable = state.isConnectivityAvailable,
        onRefresh = { /*TODO*/ },
        onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
        data = state.data,
        onNavigateToExhibitDetail = onNavigateToExhibitDetail)
}

@Composable
fun ExhibitContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        data : List<Exhibit>,
        error : String? = null,
        onRefresh : () -> Unit,
        onToggleTheme : () -> Unit,
        onNavigateToExhibitDetail : (Int) -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ExhibitTopBar(
                actions = {
                    ThemeSwitchAction(onToggleTheme)
                })
        },
        content = {
            SwipeRefresh(
                modifier = Modifier.fillMaxSize(),
                state = rememberSwipeRefreshState(isLoading) ,
                onRefresh = onRefresh ,
                swipeEnabled = isConnectivityAvailable == true)
            {
                Column {
                    if (isConnectivityAvailable != null) {
                        ConnectivityStatus(isConnectivityAvailable)
                    }
                    ExhibitList(data) { index -> onNavigateToExhibitDetail(index.id) }
                }
            }
        }
    )
}