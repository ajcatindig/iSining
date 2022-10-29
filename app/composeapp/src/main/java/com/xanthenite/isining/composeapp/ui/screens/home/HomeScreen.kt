package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ArScanAction
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.cards.featured.FeaturedContent
import com.xanthenite.isining.composeapp.component.scaffold.main.HomeTopBar
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Featured
import com.xanthenite.isining.view.viewmodel.main.HomeViewModel

@Composable
fun HomeScreen(viewModel : HomeViewModel ,
               onNavigateToAr : () -> Unit)
{
    val state by viewModel.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    HomeContent(
        isLoading = state.isLoading ,
        isConnectivityAvailable = state.isConnectivityAvailable,
        onRefresh = viewModel::getCurrentFeatured ,
        onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
        onClickScanner = onNavigateToAr,
        data = state.data)

}

@Composable
fun HomeContent(
        isLoading : Boolean ,
        isConnectivityAvailable : Boolean? ,
        data : Featured? ,
        error : String? = null ,
        onRefresh : () -> Unit ,
        onToggleTheme : () -> Unit ,
        onClickScanner : () -> Unit)
{

    ISiningScaffold(
        error = error,
        topAppBar = {
            HomeTopBar(
                actions = {
                    ArScanAction(onClickScanner)
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
                    if (data != null) {
                        FeaturedContent(data = data)
                    } else {
                        Column(modifier = Modifier
                                .fillMaxSize(),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center)
                        {
                            Row(modifier = Modifier
                                    .fillMaxWidth() ,
                                horizontalArrangement = Arrangement.Center ,
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                LottieAnimation(resId = R.raw.no_artworks ,
                                                modifier = Modifier.size(250.dp))
                            }
                            Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp , vertical = 8.dp) ,
                                horizontalArrangement = Arrangement.Center ,
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                Text(text = "No data found" ,
                                     style = MaterialTheme.typography.h5 ,
                                     fontSize = 18.sp ,
                                     textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    )
}