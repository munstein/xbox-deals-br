package com.munstein.xboxdealsbr.views.main

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModelOkHTTP : MainMVP.model{

    override fun getHTML(callback : Callback){
        var client = OkHttpClient()
        var request = Request.Builder().url("https://github.com/square/okhttp/wiki/Recipes").build()
        val response = client.newCall(request).enqueue(callback)
    }

}