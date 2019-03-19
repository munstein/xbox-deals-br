package com.munstein.xboxdealsbr.util

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.munstein.xboxdealsbr.R

object CustomTabsIntentUtil {
    fun launch(url: String, context: Context) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.customChromeTab))
        builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
        builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}