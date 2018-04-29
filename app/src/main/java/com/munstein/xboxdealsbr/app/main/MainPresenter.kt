package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter : MainContract.presenter {

    private lateinit var view: MainContract.view
    private val model: MainContract.model
    private val dealsMachine: IDealsMachine
    private lateinit var disposable: Disposable
    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    constructor(model: MainContract.model, dealsMachineJsoup: IDealsMachine) {
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

                }, {
                    view.hideDialog()
                    view.showMessage("Error!")
                    view.showErrorTitle()
                    view.showButton()
                }, {
                    view.hideDialog()
                    view.hideButton()
                })
    }

    override fun setView(view: MainContract.view) {
        this.view = view
    }

    override fun onDestroy() {
        if(disposable != null)
        if (!disposable.isDisposed)
            disposable.dispose()
    }

}