package com.xanthenite.isining.composeapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.component.ConnectivityStatus
import com.xanthenite.isining.composeapp.component.action.ShareAction
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.detail.ArtworkDetailTopbar
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.ui.theme.*
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.view.viewmodel.detail.ArtworkDetailViewModel
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import com.xanthenite.isining.utils.saveBitmap
import com.xanthenite.isining.utils.shareImage

@Composable
fun ArtworkDetailScreen(
        onNavigateUp : () -> Unit,
        viewModel : ArtworkDetailViewModel,
        onNavigateToArtist : (Int) -> Unit,
        onMakeOffer : (Int) -> Unit)
{
    val state by viewModel.collectState()
    val context = LocalContext.current

    ArtworkContent(
            data =  state.data,
            isLoading =  state.isLoading,
            isConnectivityAvailable =  state.isConnectivityAvailable,
            error =  state.error,
            onNavigateUp = onNavigateUp ,
            onRefresh = viewModel::loadArtwork ,
            onNavigateToArtist = onNavigateToArtist,
            onShareArtwork = { bitmap ->
                val uri = saveBitmap(context, bitmap.asAndroidBitmap())
                if (uri != null) {
                    context.shareImage(uri)
                }
            },
            onMakeOffer = onMakeOffer)
}

@Composable
fun ArtworkContent(
        data : Artwork,
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        error : String? ,
        onNavigateUp : () -> Unit ,
        onRefresh : () -> Unit,
        onNavigateToArtist : (Int) -> Unit,
        onShareArtwork : (ImageBitmap) -> Unit,
        onMakeOffer : (Int) -> Unit)
{
    val captureController = rememberCaptureController()

    ISiningScaffold(
        error = error,
        topAppBar = {
            ArtworkDetailTopbar(
                onNavigateUp = onNavigateUp,
                actions = {
                    ShareArtworkAction(
                        onShareArtworkAsImage = {
                            captureController.capture()
                        }
                    )
                }
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
                        ArtworkDetailBody(
                                data = data,
                                onCaptured =  onShareArtwork,
                                captureController =  captureController,
                                onNavigateToArtist =  onNavigateToArtist,
                                onMakeOffer = onMakeOffer)
                    }
                }
            }
        }
    )
}

@Composable
fun ShareArtworkAction(onShareArtworkAsImage : () -> Unit)
{
    ShareAction(onClick = onShareArtworkAsImage)
}

@Composable
fun ArtworkDetailBody(
        data : Artwork ,
        onCaptured : (ImageBitmap) -> Unit ,
        captureController: CaptureController ,
        onNavigateToArtist : (Int) -> Unit ,
        onMakeOffer : (Int) -> Unit)
{
    val isEnabled by derivedStateOf { data.sold != 1 }

    LazyColumn {
        item {
            Column(modifier = Modifier.fillMaxSize())
            {
                /**[Image]*/
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
                            Capturable(controller =  captureController,
                                       onCaptured = { bitmap, _ -> bitmap?.let(onCaptured) })
                            {
                                GlideImage(
                                    imageModel = data.pictures.first().orEmpty() ,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(250.dp) ,
                                    loading = {
                                        Box(modifier = Modifier.matchParentSize()) {
                                            CircularProgressIndicator(
                                                 modifier = Modifier.align(Alignment.Center),
                                                 color = MaterialTheme.colors.onPrimary
                                            )
                                        }
                                    } ,
                                    failure = {
                                        LottieAnimation(
                                            resId = R.raw.error_404,
                                            modifier = Modifier
                                                .matchParentSize()
                                                .align(Alignment.Center)
                                            )
                                    }
                                )
                            }
                        }
                    }
                /**[Title and Artist name]*/
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
                        Text(text = data.title.orEmpty(),
                             style = MaterialTheme.typography.h4,
                             fontSize = 25.sp,
                             maxLines = 3,
                             overflow = TextOverflow.Ellipsis)
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Text(text = "by ${data.user_name.orEmpty()}",
                             style = MaterialTheme.typography.h5,
                             fontSize = 20.sp)
                        AvailabilityChip(sold = data.sold)
                    }
                }
                /**[Description]*/
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = "About this Artwork",
                             style = MaterialTheme.typography.h5,
                             fontSize = 20.sp)
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = data.description?:"No description provided",
                             style = MaterialTheme.typography.caption,
                             fontSize = 16.sp,
                             lineHeight = 24.sp)
                    }
                }
                /**[Specs]*/
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = "Artwork Specifications",
                             style = MaterialTheme.typography.h5,
                             fontSize = 20.sp)
                    }
                    Row(modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        LengthCard(length = data.length?:"N/A")
                        WidthCard(width = data.width?:"N/A")
                        TypeCard(type = data.type?:"N/A")
                    }
                }
                /**[About]*/
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(text = "Artist Info",
                             style = MaterialTheme.typography.h5,
                             fontSize = 20.sp)
                    }
                    Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .clickable { onNavigateToArtist(data.user?.id !!) },
                         backgroundColor = lightGray,
                         elevation = 0.dp,
                         shape = RoundedCornerShape(10.dp))
                    {
                        Row(modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start)
                        {
                            GlideImage(
                                imageModel = data.user?.profile_photo_path,
                                modifier = Modifier
                                        .size(90.dp)
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
                                    val substring = data.user_name?.substring(0, 1)?.toUpperCase()
                                    Box(modifier = Modifier
                                            .matchParentSize()
                                            .background(offWhite))
                                    {
                                        Text(text = substring.orEmpty(),
                                             modifier = Modifier.align(Alignment.Center),
                                             style = MaterialTheme.typography.h4,
                                             fontSize = 40.sp ,
                                             color = lightBlue)
                                    }
                                }
                            )
                            Column(modifier = Modifier
                                    .padding(horizontal = 16.dp , vertical = 16.dp),
                                   verticalArrangement = Arrangement.Center,
                                   horizontalAlignment = Alignment.Start)
                            {
                                Text(text = data.user_name.orEmpty(),
                                     style = MaterialTheme.typography.h5,
                                     fontSize = 20.sp,
                                     color = Color.Black)
                                Row(modifier = Modifier
                                        .padding(vertical = 4.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start)
                                {
                                    Text(text = "Tap to view artist profile.",
                                         style = MaterialTheme.typography.caption,
                                         fontSize = 16.sp,
                                         color = Color.Black,
                                         maxLines = 2)
                                }
                            }
                        }
                    }
                }
                /**[Offer button]*/
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Button(
                            onClick = { onMakeOffer(data.id!!) },
                            enabled = isEnabled,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                            shape = RoundedCornerShape(10.dp))
                    {
                        Text(text = "Make an offer",
                             style = MaterialTheme.typography.h6,
                             color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun LengthCard(length : String)
{
    Card(modifier = Modifier
            .size(90.dp),
         shape = RoundedCornerShape(10.dp),
         backgroundColor = lightGray,
         elevation = 0.dp)
    {
        Column(modifier = Modifier
                .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp , bottom = 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = length,
                     style = MaterialTheme.typography.h5,
                     fontSize = 28.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Length",
                     style = MaterialTheme.typography.subtitle2,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
        }
    }
}

@Composable
private fun WidthCard(width : String)
{
    Card(modifier = Modifier
            .size(90.dp),
         shape = RoundedCornerShape(10.dp),
         backgroundColor = lightGray,
         elevation = 0.dp)
    {
        Column(modifier = Modifier
                .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp , bottom = 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = width,
                     style = MaterialTheme.typography.h5,
                     fontSize = 28.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Width",
                     style = MaterialTheme.typography.subtitle2,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
        }
    }
}

@Composable
private fun TypeCard(type : String?)
{
    Card(modifier = Modifier
            .height(90.dp)
            .width(150.dp),
         shape = RoundedCornerShape(10.dp),
         backgroundColor = lightGray,
         elevation = 0.dp)
    {
        Column(modifier = Modifier
                .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp , bottom = 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = type?:"N/A",
                     style = MaterialTheme.typography.h5,
                     fontSize = 28.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Medium",
                     style = MaterialTheme.typography.subtitle2,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Center,
                     color = Color.Black)
            }
        }
    }
}

@Composable
private fun AvailabilityChip(sold : Int?)
{
    val isSold = darkRed
    val isAvailable = darkGreen
    val Available = "AVAILABLE"
    val Sold = "SOLD"

    Card(modifier = Modifier
        .width(105.dp)
        .height(30.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = when (sold) {
                1 -> isSold
                else  -> isAvailable},
        elevation = 0.dp)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp , vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = when (sold) {
                    1 -> Sold
                    else -> Available
                },
                    style = MaterialTheme.typography.h5,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    maxLines = 1)
            }
        }
    }
}

@Preview
@Composable
fun PreviewLengthCard() = ISiningPreview {
    LengthCard(length = "12")
}