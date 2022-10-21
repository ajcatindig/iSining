package com.xanthenite.isining.composeapp.component.cards.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.xanthenite.isining.composeapp.ui.theme.lightBlue
import com.xanthenite.isining.composeapp.ui.theme.offWhite
import com.xanthenite.isining.composeapp.utils.ISiningPreview

@Composable
fun ArtistCard(
    imageUrl : String?,
    artistName : String,
    onArtistClick : () -> Unit)
{
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
                .padding(8.dp)
                .clickable { onArtistClick() },
        elevation = 2.dp)
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) 
        {
            GlideImage(
                    imageModel = imageUrl,
                    modifier = Modifier
                            .size(145.dp)
                            .clip(CircleShape),
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    failure = {
                        val substring = artistName.substring(0 , 1).toUpperCase()
                        Box(modifier = Modifier
                                .matchParentSize()
                                .background(offWhite))
                        {
                            Text(
                                    text = substring ,
                                    modifier = Modifier.align(Alignment.Center) ,
                                    style = MaterialTheme.typography.h3 ,
                                    color = lightBlue
                                )
                        }
                    }
            )
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) 
            {
                Text(
                    text = artistName,
                    style = MaterialTheme.typography.h4,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
fun PreviewArtistCard() = ISiningPreview {
    ArtistCard(
        imageUrl = "https://i.pinimg.com/originals/fd/af/66/fdaf668acc2e206e6ab80b2d614d28c6.jpg" ,
        artistName = "Tobias Forge",
        onArtistClick = {})
}