package com.munstein.xboxdealsbr.modules

import com.munstein.xboxdealsbr.app.main.IMainContract
import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.app.main.url.MainUrlProvider
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import com.munstein.xboxdealsbr.core.IDealsMachine
import com.munstein.xboxdealsbr.url.AppUrls
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by @Munstein on 06/03/2018. --20:22
 */

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideMainPresenter(model: IMainContract.Model, dealsMachineJsoup: IDealsMachine): IMainContract.Presenter {
        return MainPresenter(model, dealsMachineJsoup)
    }

    @Provides
    @Singleton
    fun provideMainModel(): IMainContract.Model {
        return MainModelOkHTTP(MainUrlProvider(AppUrls.mainUrl))
    }

    @Provides
    @Singleton
    fun provideDealsMachine(): IDealsMachine {
        return DealsMachineJsoup()
    }
}