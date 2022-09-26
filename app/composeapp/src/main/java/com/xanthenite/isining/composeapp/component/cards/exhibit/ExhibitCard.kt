package com.xanthenite.isining.composeapp.component.cards.exhibit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.utils.ISiningPreview

@Composable
fun ExhibitCard(
        imageUrl : String,
        title : String,
        startDate : String,
        endDate : String,
        onExhibitClick : () -> Unit)
{
    Card(
       shape = RoundedCornerShape(15.dp),
       backgroundColor = MaterialTheme.colors.surface,
       modifier = Modifier
               .padding(horizontal = 16.dp , vertical = 8.dp)
               .fillMaxWidth()
               .wrapContentHeight()
               .clickable { onExhibitClick() },
       elevation = 0.dp)
    {
        Row(modifier = Modifier.padding(16.dp))
        {
            GlideImage(
                imageModel = imageUrl,
                modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(15.dp)),
                loading = {
                    Box(modifier = Modifier.matchParentSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = MaterialTheme.colors.onPrimary)
                    }
                }, // shows an error text message when request failed.
                failure = { LottieAnimation(resId = R.raw.error_404,
                                            modifier = Modifier
                                                    .matchParentSize()
                                                    .align(Alignment.Center)) }
//                placeHolder = painterResource(id = R.drawable.app_logo),
//                error = painterResource(id = R.drawable.app_logo)
            )
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                   verticalArrangement = Arrangement.Center)
            {
                Text(text = title,
                     maxLines = 2,
                     overflow = TextOverflow.Ellipsis,
                     style = MaterialTheme.typography.h5,
                     fontSize = 24.sp)
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Text(text = "Start Date: $startDate",
                         style = MaterialTheme.typography.caption,
                         fontSize = 14.sp)
                }
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Text(text = "End Date: $endDate",
                         style = MaterialTheme.typography.caption,
                         fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExhibitCard() = ISiningPreview {
    ExhibitCard(imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Johnrogershousemay2020.webp/1200px-Johnrogershousemay2020.webp.png",
                title = "Lorem Ipsum" ,
                startDate = "09-09-2022",
                endDate = "09-10-2022",
                onExhibitClick = {})
}