package com.xanthenite.isining.composeapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkLazyRow
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkList
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.detail.ExhibitDetailTopbar
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.view.viewmodel.detail.ExhibitDetailViewModel

@Composable
fun ExhibitDetailScreen(
        onNavigateUp : () -> Unit,
        viewModel : ExhibitDetailViewModel,
        onNavigateToArtworkDetail : (Int) -> Unit)
{
    val state by viewModel.collectState()

    ExhibitContent(
            data =  state.data ,
            error =  state.error ,
            isLoading = state.isLoading ,
            isConnectivityAvailable = state.isConnectivityAvailable,
            onRefresh = viewModel::loadExhibit,
            onNavigateUp = onNavigateUp ,
            onNavigateToArtwork = onNavigateToArtworkDetail)
}


@Composable
fun ExhibitContent(
        data : Exhibit ,
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        error : String? ,
        onNavigateUp : () -> Unit ,
        onRefresh : () -> Unit,
        onNavigateToArtwork : (Int) -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ExhibitDetailTopbar(
                onNavigateUp = onNavigateUp)
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
                    Surface(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface))
                    {
                        LazyColumn {
                            item {
                                Column(modifier = Modifier
                                        .fillMaxSize())
                                {
                                    //Image
                                    Column(modifier = Modifier.fillMaxWidth(),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 16.dp),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            GlideImage(
                                                imageModel = data.cover_path,
                                                modifier = Modifier
                                                        .fillMaxWidth()
                                                        .size(250.dp),
                                                loading = {
                                                    Box(modifier = Modifier.matchParentSize()) {
                                                        CircularProgressIndicator(
                                                                modifier = Modifier.align(Alignment.Center),
                                                                color = MaterialTheme.colors.onPrimary
                                                        )
                                                    }
                                                },
                                                failure = {
                                                    LottieAnimation(
                                                        resId = R.raw.error_404 ,
                                                        modifier = Modifier
                                                                .matchParentSize()
                                                                .align(Alignment.Center)
                                                    )
                                                }
                                            )
                                        }
                                    }
                                    //Title and date
                                    Column(modifier = Modifier
                                            .fillMaxWidth(),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start)
                                        {
                                            Text(text = data.title.orEmpty() ,
                                                 style = MaterialTheme.typography.h4 ,
                                                 fontSize = 25.sp ,
                                                 maxLines = 3 ,
                                                 overflow = TextOverflow.Ellipsis)
                                        }
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start)
                                        {
                                            Text(text = "From ${data.start_date.orEmpty()} until ${data.end_date.orEmpty()}",
                                                 style = MaterialTheme.typography.h5 ,
                                                 fontSize = 18.sp)
                                        }
                                    }
                                    //Description
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                           verticalArrangement = Arrangement.Center,
                                           horizontalAlignment = Alignment.CenterHorizontally)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp , top = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start)
                                        {
                                            Text(text = "About this Exhibit",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start)
                                        {
                                            Text(text = data.description.orEmpty(),
                                                 style = MaterialTheme.typography.caption,
                                                 fontSize = 16.sp,
                                                 lineHeight = 24.sp)
                                        }
                                    }
                                    //Artwork List
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                           verticalArrangement = Arrangement.Center,
                                           horizontalAlignment = Alignment.CenterHorizontally)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp , top = 16.dp , bottom = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start)
                                        {
                                            Text(text = "Artworks in this Exhibit",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        data.artworks?.let { it -> ArtworkLazyRow(it) { index -> onNavigateToArtwork(index.id!!) } }
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