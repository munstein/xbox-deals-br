package com.munstein.xboxdealsbr.views.main

import com.munstein.xboxdealsbr.core.DealsMachine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainMVP.presenter, Callback<String> {

    val view : MainMVP.view
    val model : MainMVP.model
    val dealsMachine : DealsMachine

    constructor(model: MainMVP.model, view: MainMVP.view) {
        this.model = model
        this.view = view
        dealsMachine = DealsMachine()
    }

    override fun displayDeals() {
        view.showDialog()
        model.getHTML(this)
    }

    override fun onResponse(call: Call<String>?, response: Response<String>?) {
        var html = response!!.body()
        var dealsList = dealsMachine.getLatestDealsFromHTML(html!!)
        view.loadDeals(dealsList)
        view.hideDialog()
    }

    override fun onFailure(call: Call<String>?, t: Throwable?) {
        view.showMessage("Erro!")
    }

}