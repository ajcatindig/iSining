package com.xanthenite.isining.composeapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.xanthenite.isining.composeapp.component.action.ThemeSwitchAction
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkLazyRow
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkList
import com.xanthenite.isining.composeapp.component.list.exhibit.CurrentExhibitRow
import com.xanthenite.isining.composeapp.component.list.exhibit.ExhibitList
import com.xanthenite.isining.composeapp.component.list.exhibit.PastExhibitRow
import com.xanthenite.isining.composeapp.component.list.exhibit.UpcomingExhibitRow
import com.xanthenite.isining.composeapp.component.scaffold.main.ExhibitTopBar
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.view.viewmodel.main.CurrentExhibitsViewModel
import com.xanthenite.isining.view.viewmodel.main.ExhibitViewModel
import com.xanthenite.isining.view.viewmodel.main.PastExhibitsViewModel
import com.xanthenite.isining.view.viewmodel.main.UpcomingExhibitsViewModel

@Composable
fun ExhibitScreen(
        viewModel : ExhibitViewModel,
        viewModel1 : CurrentExhibitsViewModel,
        viewModel2 : PastExhibitsViewModel,
        viewModel3 : UpcomingExhibitsViewModel,
        onNavigateToExhibitDetail: (Int) -> Unit)
{
    val state by viewModel.collectState()

    val state1 by viewModel1.collectState()

    val state2 by viewModel2.collectState()

    val state3 by viewModel3.collectState()

    val isInDarkMode = isSystemInDarkTheme()

    ExhibitContent(
        isLoading = state.isLoading ,
        isConnectivityAvailable = state.isConnectivityAvailable,
        onRefresh = viewModel::getAllExhibits,
        onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
        data1 = state1.data,
        data2 = state2.data,
        data3 = state3.data,
        onNavigateToExhibitDetail = onNavigateToExhibitDetail)
}

@Composable
fun ExhibitContent(
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        data1 : List<Exhibit>,
        data2 : List<Exhibit>,
        data3 : List<Exhibit>,
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
                }
            )
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
                    Surface(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface))
                    {
                        LazyColumn {
                            item {
                                Column(modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp , bottom = 10.dp))
                                {
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Current Exhibits",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        if (data1.isNotEmpty()) {
                                            CurrentExhibitRow(data1){ index -> onNavigateToExhibitDetail(index.id!!) }
                                        } else {
                                            Row(modifier = Modifier
                                                    .fillMaxWidth() ,
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                LottieAnimation(resId = R.raw.no_artworks ,
                                                                modifier = Modifier.size(200.dp))
                                            }
                                            Row(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp , vertical = 8.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                Text(text = "No exhibits found",
                                                     style = MaterialTheme.typography.h5,
                                                     fontSize = 18.sp,
                                                     textAlign = TextAlign.Center)
                                            }
                                        }
                                    }
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Past Exhibits",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        if (data2.isNotEmpty()) {
                                            PastExhibitRow(data2){ index -> onNavigateToExhibitDetail(index.id!!) }
                                        } else {
                                            Row(modifier = Modifier
                                                    .fillMaxWidth() ,
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                LottieAnimation(resId = R.raw.no_artworks ,
                                                                modifier = Modifier.size(200.dp))
                                            }
                                            Row(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp , vertical = 8.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                Text(text = "No exhibits found",
                                                     style = MaterialTheme.typography.h5,
                                                     fontSize = 18.sp,
                                                     textAlign = TextAlign.Center)
                                            }
                                        }
                                    }
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Upcoming Exhibits",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        if (data3.isNotEmpty()) {
                                            UpcomingExhibitRow(data3)
                                        } else {
                                            Row(modifier = Modifier
                                                    .fillMaxWidth() ,
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                LottieAnimation(resId = R.raw.no_artworks ,
                                                                modifier = Modifier.size(200.dp))
                                            }
                                            Row(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp , vertical = 8.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                Text(text = "No exhibits found",
                                                     style = MaterialTheme.typography.h5,
                                                     fontSize = 18.sp,
                                                     textAlign = TextAlign.Center)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}