package com.xanthenite.isining.composeapp.component.cards.artwork

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.R

@Composable
fun ArtworkCard(
        imageUrl : String,
        title : String,
        artistName : String,
        onArtworkClick : () -> Unit)
{
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp , vertical = 8.dp)
                .clickable { onArtworkClick() },
        elevation = 2.dp)
    {
        GlideImage(
            imageModel = imageUrl,
            modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                           .align(Alignment.Center),
                        color = MaterialTheme.colors.onPrimary)
                }
            },
            failure = {
                LottieAnimation(
                    resId = R.raw.error_404,
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.Center)
                )
            }
        )
        Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .background(MaterialTheme.colors.primary.copy(0.6f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center)
            {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontSize = 28.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center)
            {
                Text(
                    text = "by $artistName",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewArtworkCard() = ISiningPreview {
    ArtworkCard(
        imageUrl = "https://i0.wp.com/www.wikimetal.com.br/wp-content/uploads/2022/01/ghost.jpg?resize=1170%2C658&ssl=1" ,
        title = "Mary On A Cross",
        artistName = "Papa Emeritus IV",
        onArtworkClick = {})
}
