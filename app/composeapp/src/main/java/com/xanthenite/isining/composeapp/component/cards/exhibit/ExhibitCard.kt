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
import androidx.compose.ui.text.font.FontStyle
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
        imageUrl : String?,
        title : String,
        startDate : String,
        endDate : String,
        onExhibitClick : () -> Unit)
{
    Card(
       shape = RoundedCornerShape(10.dp),
       backgroundColor = MaterialTheme.colors.surface,
       modifier = Modifier
               .padding(horizontal = 16.dp , vertical = 4.dp)
               .fillMaxWidth()
               .wrapContentHeight()
               .clickable { onExhibitClick() },
       elevation = 2.dp)
    {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(0.dp))
        {
            GlideImage(
                imageModel = imageUrl,
                modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
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
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.Start)
            {
                Text(text = title,
                     maxLines = 2,
                     overflow = TextOverflow.Ellipsis,
                     style = MaterialTheme.typography.h5,
                     fontSize = 20.sp)
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "From: $startDate",
                         style = MaterialTheme.typography.subtitle2,
                         fontSize = 14.sp,
                         fontStyle = FontStyle.Italic)
                }
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.Start)
                {
                    Text(text = "Until: $endDate",
                         style = MaterialTheme.typography.subtitle2,
                         fontSize = 14.sp,
                         fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExhibitCard() = ISiningPreview {
    ExhibitCard(
        imageUrl = "https://i.scdn.co/image/ab67616d0000b273bef9b0a348ea8dd18a581025",
        title = "Seven Inches of Satanic Panic",
        startDate = "09-09-2022",
        endDate = "09-10-2022",
        onExhibitClick = {})
}