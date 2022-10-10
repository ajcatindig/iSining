package com.xanthenite.isining.composeapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.list.artwork.ArtworkList
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.core.model.Exhibit

@Composable
fun ExhibitDetailScreen(
        onNavigateUp : () -> Unit)
{

}


@Composable
fun ExhibitContent()
{

}

@Composable
fun ExhibitDetails(
        data : Exhibit ,
        onNavigateToArtwork : (Int) -> Unit ,
        onNavigateUp : () -> Unit
                  )
{
    Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.surface)
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
                            .size(230.dp),
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
                Text(text = data.title,
                     style = MaterialTheme.typography.h4,
                     fontSize = 25.sp,
                     maxLines = 2,
                     overflow = TextOverflow.Ellipsis)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = data.start_date,
                     style = MaterialTheme.typography.caption,
                     fontSize = 16.sp)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = data.end_date,
                     style = MaterialTheme.typography.caption,
                     fontSize = 16.sp)
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
                Text(text = "About the Exhibit",
                     style = MaterialTheme.typography.h5,
                     fontSize = 20.sp)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = data.description,
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
                    .padding(start = 16.dp , end = 16.dp , top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Artworks in this Exhibit",
                     style = MaterialTheme.typography.h5,
                     fontSize = 20.sp)
            }
            ArtworkList(data.artwork) { index -> onNavigateToArtwork(index.id!!) }
        }
    }
}

//@Preview
//@Composable
//fun PreviewExhibitDetails() = ISiningPreview {
//    ExhibitDetails()
//}