package com.munstein.xboxdealsbr.modules

import com.munstein.xboxdealsbr.app.main.MainContract
import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import com.munstein.xboxdealsbr.core.IDealsMachine
import dagger.Module
import dagger.Provides

/**
 * Created by @Munstein on 06/03/2018. --20:22
 */

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(model: MainContract.Model, dealsMachineJsoup: IDealsMachine): MainContract.Presenter {
        return MainPresenter(model, dealsMachineJsoup)
    }

    @Provides
    fun provideMainModel(): MainContract.Model {
        return MainModelOkHTTP()
    }

    @Provides
    fun provideDealsMachine(): IDealsMachine {
        return DealsMachineJsoup()
    }
}