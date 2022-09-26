package com.xanthenite.isining.composeapp.component.cards.artwork

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.cards.exhibit.ExhibitCard
import com.xanthenite.isining.composeapp.utils.ISiningPreview

@Composable
fun ArtworkCard(
        imageUrl : String,
        title : String,
        artistName : String,
        onArtworkClick : () -> Unit)
{
    Card(
        shape = RoundedCornerShape(10.dp) ,
        backgroundColor = MaterialTheme.colors.surface ,
        modifier = Modifier
                .padding(horizontal = 16.dp , vertical = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onArtworkClick() },
        elevation = 0.dp)
    {

    }
}

@Preview
@Composable
fun PreviewArtworkCard() = ISiningPreview {
    ArtworkCard(
        imageUrl = "" ,
        title = "Defiance",
        artistName = "X AE 12",
        onArtworkClick = {})
}
