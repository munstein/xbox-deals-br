package com.munstein.xboxdealsbr.components

import com.munstein.xboxdealsbr.app.main.MainActivity
import com.munstein.xboxdealsbr.modules.MainModule
import dagger.Component

/**
 * Created by @Munstein on 06/03/2018. --20:34
 */
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(target : MainActivity)
}