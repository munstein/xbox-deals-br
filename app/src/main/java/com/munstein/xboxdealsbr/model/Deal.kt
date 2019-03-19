package com.munstein.xboxdealsbr.model

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

/**
 * Created by @Munstein on 17/01/2018. --23:33
 */
data class Deal(
    val game: String,
    val type: String,
    val discount: String,
    val value: String,
    val url: String
)