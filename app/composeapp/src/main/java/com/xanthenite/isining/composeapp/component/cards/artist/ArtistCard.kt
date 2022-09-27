package com.xanthenite.isining.composeapp.component.cards.artist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.utils.ISiningPreview

@Composable
fun ArtistCard(
    imageUrl : String,
    artistName : String,
    artistEmail : String,
    onArtistClick : () -> Unit)
{
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp , vertical = 8.dp)
                .clickable { onArtistClick() },
        elevation = 2.dp)
    {
        Row(modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start)
        {
            GlideImage(
                imageModel = imageUrl,
                modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colors.onPrimary, CircleShape))
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start)
            {
                Text(text = artistName, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = artistEmail, style = MaterialTheme.typography.caption, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewArtistCard() = ISiningPreview {
    ArtistCard(
        imageUrl = "https://images.genius.com/15fc69cd7662c307f69188b951f80622.608x608x1.jpg" ,
        artistName = "Tobias Forge",
        artistEmail = "emeritusiv@gmail.com",
        onArtistClick = {})
}