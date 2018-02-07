package com.munstein.xboxdealsbr

import okhttp3.*
import org.junit.Assert
import org.junit.Test

/**
 * Created by @Munstein on 05/02/2018. --21:55
 */
class ServicesTest {

    @Test
    fun testBasicOkHTTP(){
        var client = OkHttpClient()
        var request = Request.Builder().url("https://github.com/square/okhttp/wiki/Recipes").build()
        val response = client.newCall(request).execute()
        Assert.assertEquals(true,response.body()!!.string().contains("<!DOCTYPE html>"))
    }

}