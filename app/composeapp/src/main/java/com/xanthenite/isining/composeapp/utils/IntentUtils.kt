package com.xanthenite.isining.composeapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

object IntentUtils {
    fun launchBrowser(context : Context, url : String) =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                context.startActivity(it)
            }

    fun launchGallery(context : Context) =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
                context.startActivity(it)
            }
}