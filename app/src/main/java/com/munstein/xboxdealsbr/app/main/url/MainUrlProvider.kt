package com.munstein.xboxdealsbr.app.main.url

class MainUrlProvider(val url: String) : IMainUrlProvider {

    override fun getMainUrl(): String {
        return url
    }
}