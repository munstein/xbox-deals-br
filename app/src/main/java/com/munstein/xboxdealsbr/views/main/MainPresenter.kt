package com.munstein.xboxdealsbr.views.main

import com.munstein.xboxdealsbr.core.DealsMachine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainMVP.presenter, Callback {


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


    override fun onResponse(call: Call?, response: Response?) {
        var html = response!!.body()!!.string()
        var dealsList = dealsMachine.getLatestDealsFromHTML(html!!)
        view.loadDeals(dealsList)
        view.hideDialog()
    }

    override fun onFailure(call: Call?, e: IOException?) {
        view.showMessage("Erro!")
    }

}