package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.list.artist.ArtistList
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.main.ArtistTopBar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.view.viewmodel.main.ArtistViewModel

@Composable
fun ArtistScreen(
    viewModel : ArtistViewModel,
    onNavigateToArtistDetail : (Int) -> Unit)
{
    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    ArtistContent(
            isLoading = state.isLoading ,
            isConnectivityAvailable = state.isConnectivityAvailable,
            onRefresh = viewModel::getALlArtists,
            onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
            data = state.data,
            onNavigateToArtistDetail = onNavigateToArtistDetail)
}

@Composable
fun ArtistContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        data : List<Artist>,
        error : String? = null,
        onRefresh : () -> Unit,
        onToggleTheme : () -> Unit,
        onNavigateToArtistDetail : (Int) -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ArtistTopBar(
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
                    ArtistList(data) { index -> onNavigateToArtistDetail(index.id) }
                }
            }
        }
    )
}