package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkList
import com.xanthenite.isining.composeapp.component.list.exhibit.ExhibitList
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.main.ArtworkTopBar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.view.viewmodel.main.ArtworkViewModel

@Composable
fun ArtworkScreen(
    viewModel : ArtworkViewModel,
    onNavigateToArtworkDetail : (Int) -> Unit)
{
    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    ArtworkContent(
            isLoading = state.isLoading ,
            isConnectivityAvailable = state.isConnectivityAvailable,
            onRefresh = viewModel::getAllArtworks,
            data = state.data,
            onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
            onNavigateToArtworkDetail = onNavigateToArtworkDetail)
}

@Composable
fun ArtworkContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        data : List<Artwork>,
        error : String? = null,
        onRefresh : () -> Unit,
        onToggleTheme : () -> Unit,
        onNavigateToArtworkDetail : (Int) -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ArtworkTopBar(
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
                    ArtworkList(data){ index -> onNavigateToArtworkDetail(index.id) }
                }
            }
        }
    )
}