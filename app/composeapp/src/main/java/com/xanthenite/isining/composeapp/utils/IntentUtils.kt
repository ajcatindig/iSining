package com.xanthenite.isining.composeapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentUtils {
    fun launchBrowser(context : Context, url : String) =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                context.startActivity(it)
            }
}