package com.xanthenite.isining.composeapp.component.cards.featured

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.cards.exhibit.ExhibitCard
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.core.model.Featured

@Composable
fun FeaturedContent(data : Featured)
{
    Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface))
    {
        LazyColumn {
            item {
                Column {
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp , end = 24.dp , top = 24.dp , bottom = 16.dp),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(
                                    text = "Artist of the Month",
                                    style = MaterialTheme.typography.h5,
                                    textAlign = TextAlign.Start,
                                    fontSize = 25.sp)
                        }
                    }
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 24.dp , end = 24.dp , bottom = 16.dp),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center)
                        {
                            GlideImage(
                                    imageModel = data.user.profile_photo_path ,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp)),
                                    loading = {
                                        Box(modifier = Modifier.matchParentSize()) {
                                            CircularProgressIndicator(
                                                    modifier = Modifier
                                                            .align(Alignment.Center),
                                                    color = MaterialTheme.colors.onPrimary)
                                        }
                                    } ,
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
                    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.Start)
                    {
                        Text(
                                text = data.user.name.orEmpty(),
                                style = MaterialTheme.typography.h4,
                                fontSize = 30.sp)
                        Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp , bottom = 24.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = data.user.bio?:"No bio provided.",
                                 style = MaterialTheme.typography.caption,
                                 fontSize = 16.sp,
                                 lineHeight = 24.sp)
                        }
                    }
                    Column(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp , end = 24.dp , top = 24.dp , bottom = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = "Artwork of the Month",
                                 style = MaterialTheme.typography.h5,
                                 textAlign = TextAlign.Start,
                                 fontSize = 25.sp)
                        }
                    }
                    Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 24.dp , end = 24.dp , bottom = 16.dp),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center)
                        {
                            GlideImage(
                                    imageModel = data.artwork.pictures.first().orEmpty() ,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp)),
                                    loading = {
                                        Box(modifier = Modifier.matchParentSize()) {
                                            CircularProgressIndicator(
                                                    modifier = Modifier
                                                            .align(Alignment.Center),
                                                    color = MaterialTheme.colors.onPrimary)
                                        }
                                    } ,
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
                    Column(modifier = Modifier.padding(horizontal = 24.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.Start)
                    {
                        Text(
                                text = data.artwork.title.orEmpty(),
                                style = MaterialTheme.typography.h4,
                                fontSize = 30.sp)
                        Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = "by ${data.artwork.user_name.orEmpty()}",
                                 style = MaterialTheme.typography.subtitle1,
                                 fontSize = 25.sp)
                        }
                        Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp , bottom = 24.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = data.artwork.description.orEmpty(),
                                 style = MaterialTheme.typography.caption,
                                 fontSize = 16.sp,
                                 lineHeight = 24.sp)
                        }
                    }
                }
            }
        }
    }
}