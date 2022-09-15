package com.xanthenite.isining.composeapp.component.anim

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation(
        @RawRes resId: Int ,
        modifier: Modifier = Modifier ,
        iterations: Int = LottieConstants.IterateForever ,
        restartOnPlay: Boolean = true)
{
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(
            composition,
            iterations = iterations,
            restartOnPlay = restartOnPlay
                                                   )

    com.airbnb.lottie.compose.LottieAnimation(composition , progress , modifier = modifier)
}