package com.munstein.xboxdealsbr.views.main

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModelOkHTTP : MainMVP.model {

    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    override fun getHTML(callback: Callback) {
        var client = OkHttpClient()
        var request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }

}