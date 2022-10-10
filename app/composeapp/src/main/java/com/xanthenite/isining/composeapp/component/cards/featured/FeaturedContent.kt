package com.xanthenite.isining.composeapp.component.cards.featured

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
    {
        //Artist of the month
        Card(shape = RoundedCornerShape(0.dp),
             backgroundColor = MaterialTheme.colors.surface,
             modifier = Modifier
                     .fillMaxWidth()
                     .wrapContentHeight()
                     .padding(bottom = 2.dp),
             elevation = 0.dp
            )
        {
            Column {
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp),
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
                            .padding(top = 16.dp, bottom = 24.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) 
                    {
                        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
                                    "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                                    "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa " +
                                    "quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                                    "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. " +
                                    "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora " +
                                    "incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis " +
                                    "suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse " +
                                    "quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
                            style = MaterialTheme.typography.caption,
                            fontSize = 16.sp,
                            lineHeight = 24.sp)
                    }
                }
            }
        }

        //Artwork of the month
        Card(shape = RoundedCornerShape(0.dp),
             backgroundColor = MaterialTheme.colors.surface,
             modifier = Modifier
                     .fillMaxWidth()
                     .wrapContentHeight()
                     .padding(bottom = 0.dp),
             elevation = 2.dp
            )
        {
            Column {
                Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp),
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
                            imageModel = data.artwork.pictures.first() ,
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
                             fontSize = 25.sp,
                             fontStyle = FontStyle.Italic)
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 24.dp),
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