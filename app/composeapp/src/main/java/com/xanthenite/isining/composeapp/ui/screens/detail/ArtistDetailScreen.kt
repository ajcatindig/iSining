package com.xanthenite.isining.composeapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.detail.ArtistDetailTopbar
import com.xanthenite.isining.composeapp.ui.theme.lightBlue
import com.xanthenite.isining.composeapp.ui.theme.offWhite
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.view.viewmodel.detail.ArtistDetailViewModel

@Composable
fun ArtistDetailScreen(
        onNavigateUp : () -> Unit,
        viewModel : ArtistDetailViewModel,
        onNavigateToArtwork : (Int) -> Unit,
        onHireArtist : (Int) -> Unit)
{
    val state by viewModel.collectState()

    ArtistContent(
            data =  state.data,
            isLoading =  state.isLoading,
            isConnectivityAvailable =  state.isConnectivityAvailable,
            error =  state.error,
            onNavigateUp = onNavigateUp ,
            onRefresh = viewModel::loadArtist ,
            onNavigateToArtwork = onNavigateToArtwork,
            onHireArtist = onHireArtist)
}

@Composable
fun ArtistContent(
        data : Artist ,
        isLoading : Boolean ,
        isConnectivityAvailable : Boolean? ,
        error : String? ,
        onNavigateUp : () -> Unit ,
        onRefresh : () -> Unit ,
        onNavigateToArtwork : (Int) -> Unit,
        onHireArtist : (Int) -> Unit)
{
    ISiningScaffold(
        error = error,
        topAppBar = {
            ArtistDetailTopbar(
                onNavigateUp = onNavigateUp
            )
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
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                Column(modifier = Modifier
                                        .fillMaxSize())
                                {
                                    Row(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp , vertical = 24.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center)
                                    {
                                            Row(modifier = Modifier.padding(end = 16.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                GlideImage(
                                                    imageModel = data.profile_photo_path,
                                                    modifier = Modifier
                                                            .size(130.dp)
                                                            .clip(CircleShape),
                                                    loading = {
                                                        Box(modifier = Modifier.matchParentSize())
                                                        {
                                                            CircularProgressIndicator(
                                                                    modifier = Modifier.align(Alignment.Center),
                                                                    color = MaterialTheme.colors.onPrimary
                                                            )
                                                        }
                                                    },
                                                    failure = {
                                                        val substring = data.name?.substring(0 , 1)?.toUpperCase()
                                                        Box(modifier = Modifier
                                                                .matchParentSize()
                                                                .background(offWhite))
                                                        {
                                                            Text(
                                                                text = substring.orEmpty(),
                                                                modifier = Modifier.align(Alignment.Center),
                                                                style = MaterialTheme.typography.h4,
                                                                fontSize = 50.sp,
                                                                color = lightBlue)
                                                        }
                                                    }
                                                )
                                            }
                                            Column(modifier = Modifier
                                                    .padding(vertical = 16.dp),
                                                   verticalArrangement = Arrangement.Center,
                                                   horizontalAlignment = Alignment.Start)
                                            {
                                                Text(text = data.name.orEmpty(),
                                                     maxLines = 2,
                                                     overflow = TextOverflow.Ellipsis,
                                                     style = MaterialTheme.typography.h5,
                                                     fontSize = 20.sp)
                                                Row(modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp),
                                                    horizontalArrangement = Arrangement.Start)
                                                {
                                                    Text(text = data.email ?: "Not Available",
                                                         style = MaterialTheme.typography.subtitle2,
                                                         fontSize = 16.sp,)
                                                }
                                                Row(modifier = Modifier
                                                        .padding(bottom = 8.dp)
                                                        .fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Start)
                                                {
                                                    Text(text = data.number ?: "No number provided",
                                                         style = MaterialTheme.typography.subtitle2,
                                                         fontSize = 16.sp)
                                                }
                                            }
                                    }
                                    //Artist Bio
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp , end = 16.dp , bottom = 16.dp, top = 24.dp),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Artist Bio",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        Row(modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = data.bio ?: "No bio provided",
                                                 style = MaterialTheme.typography.caption,
                                                 fontSize = 16.sp,
                                                 lineHeight = 24.sp)
                                        }
                                    }
                                    //Artworks list
                                    Column(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp, bottom = 8.dp),
                                           horizontalAlignment = Alignment.CenterHorizontally,
                                           verticalArrangement = Arrangement.Center)
                                    {
                                        Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Artist Artworks",
                                                 style = MaterialTheme.typography.h5,
                                                 fontSize = 20.sp)
                                        }
                                        if (data.artwork.isNotEmpty()) {
                                            ArtworkLazyRow(data.artwork) { index -> onNavigateToArtwork(index.id!!) }
                                        } else {
                                            Row(modifier = Modifier
                                                    .fillMaxWidth() ,
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                LottieAnimation(resId = R.raw.no_artworks,
                                                                modifier = Modifier.size(250.dp))
                                            }
                                            Row(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp , vertical = 8.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically)
                                            {
                                                Text(text = "No artworks found",
                                                     style = MaterialTheme.typography.h5,
                                                     fontSize = 18.sp,
                                                     textAlign = TextAlign.Center)
                                            }
                                        }
                                    }
                                }
                            }
                            item {
                                //Offer button
                                Column(modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                       verticalArrangement = Arrangement.Bottom,
                                       horizontalAlignment = Alignment.CenterHorizontally)
                                {
                                    Button(
                                            onClick = { onHireArtist(data.id!!) } ,
                                            modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(55.dp) ,
                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                                            shape = RoundedCornerShape(10.dp)
                                          )
                                    {
                                        Text(text = "Hire this artist",
                                             style = MaterialTheme.typography.h6,
                                             color = Color.White)
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