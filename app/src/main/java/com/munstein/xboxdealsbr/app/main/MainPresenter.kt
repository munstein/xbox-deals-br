package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.reactivestreams.Subscription
import java.io.IOException

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainMVP.presenter{

    private lateinit var view: MainMVP.view
    private val model: MainMVP.model
    private val dealsMachine: IDealsMachine
    private lateinit var disposable: Disposable
    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    constructor(model : MainMVP.model, dealsMachineJsoup: IDealsMachine){
        this.model = model
        this.dealsMachine = dealsMachineJsoup
    }

    override fun displayDeals() {
        view.showDialog()
        disposable = model.getHTML(url)
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
                },{
                    view.hideDialog()
                })
    }

    override fun setView(view: MainMVP.view) {
        this.view = view
    }

    override fun onPause() {
        if(!disposable.isDisposed)
            disposable.dispose()
    }

}