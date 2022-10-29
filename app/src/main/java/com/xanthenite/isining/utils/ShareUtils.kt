package com.xanthenite.isining.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat

fun Context.shareImage(imageUri: Uri) {
    val intent = ShareCompat.IntentBuilder(this)
            .setType("image/jpeg")
            .setStream(imageUri)
            .intent

    startActivity(Intent.createChooser(intent , null))
}