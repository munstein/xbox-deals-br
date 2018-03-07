package com.munstein.xboxdealsbr.base

import android.app.Application
import com.munstein.xboxdealsbr.components.DaggerMainComponent
import com.munstein.xboxdealsbr.components.MainComponent
import com.munstein.xboxdealsbr.modules.MainModule


/**
 * Created by @Munstein on 06/03/2018. --18:32
 */
class BaseApp : Application() {

    private lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder()
                .mainModule(MainModule())
                .build()
    }

    fun getComponent(): MainComponent {
        return component
    }

}