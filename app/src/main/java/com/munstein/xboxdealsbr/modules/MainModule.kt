package com.munstein.xboxdealsbr.modules

import com.munstein.xboxdealsbr.app.main.MainMVP
import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import com.munstein.xboxdealsbr.app.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by @Munstein on 06/03/2018. --20:22
 */

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(model : MainMVP.model) : MainMVP.presenter{
        return MainPresenter(model)
    }

    @Provides
    fun provideMainModel() : MainMVP.model{
        return MainModelOkHTTP()
    }

}