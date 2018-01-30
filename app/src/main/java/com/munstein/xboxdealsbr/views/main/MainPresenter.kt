package com.munstein.xboxdealsbr.views.main

import retrofit2.Retrofit

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainMVP.presenter {

    val view : MainMVP.view
    val model : MainMVP.model

    constructor(model: MainMVP.model, view: MainMVP.view) {


        this.model = model
        this.view = view
    }

    override fun displayDeals() {

    }

}