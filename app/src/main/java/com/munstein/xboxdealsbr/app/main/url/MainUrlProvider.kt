package com.munstein.xboxdealsbr.app.main.url

import javax.inject.Inject
import javax.inject.Named

class MainUrlProvider : IMainUrlProvider {

    @Inject
    @Named("MAIN_URL")
    lateinit var url: String

    override fun getMainUrl(): String {
        return url
    }
}