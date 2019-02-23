package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */
class MainPresenter(private val model: MainContract.model, dealsMachineJsoup: IDealsMachine) : MainContract.presenter {

    private lateinit var view: MainContract.view
    private val dealsMachine: IDealsMachine = dealsMachineJsoup
    private lateinit var disposable: Disposable
    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    override fun displayDeals() {
        view.showProgress()
        disposable = model.getHTML(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({ html ->
                    val dealsList = dealsMachine.getLatestDealsFromHTML(html!!)
                    val title = dealsMachine.getTitle(html)
                    view.loadDeals(dealsList)
                    view.loadTitle(title)

                }, {
                    view.hideProgress()
                    view.showMessage("Error!")
                    view.showErrorTitle()
                    view.showReloadFab()
                }, {
                    view.hideProgress()
                    view.hideReloadFab()
                })
    }

    override fun setView(view: MainContract.view) {
        this.view = view
    }

    override fun onDestroy() {
        if (disposable != null)
            if (!disposable.isDisposed)
                disposable.dispose()
    }

}