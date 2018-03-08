package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainMVP.presenter, Callback {
    private lateinit var view: MainMVP.view
    private val model: MainMVP.model
    private val dealsMachine: IDealsMachine

    constructor(model : MainMVP.model, dealsMachineJsoup: IDealsMachine){
        this.model = model
        this.dealsMachine = dealsMachineJsoup
    }

    override fun displayDeals() {
        view.showDialog()
        model.getHTML()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({
                    var html = it
                    var dealsList = dealsMachine.getLatestDealsFromHTML(html!!)
                    var title = dealsMachine.getTitle(html)
                    view.loadDeals(dealsList)
                    view.loadTitle(title)
                    view.hideDialog()
                }, {
                    view.hideDialog()
                    view.showMessage("Error!")
                })
    }

    override fun onResponse(call: Call?, response: Response?) {
        var html = response!!.body()!!.string()
        var dealsList = dealsMachine.getLatestDealsFromHTML(html!!)
        var title = dealsMachine.getTitle(html)
        view.loadDeals(dealsList)
        view.loadTitle(title)
        view.hideDialog()
    }

    override fun onFailure(call: Call?, e: IOException?) {
        view.hideDialog()
        view.showMessage("Error!")
    }

    override fun setView(view: MainMVP.view) {
        this.view = view
    }

}