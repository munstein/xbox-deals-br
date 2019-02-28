package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */

class MainPresenter(private val model: MainContract.Model, dealsMachineJsoup: IDealsMachine) : MainContract.Presenter {

    private lateinit var view: MainContract.View
    private val dealsMachine: IDealsMachine = dealsMachineJsoup
    private val disposable by lazy { CompositeDisposable() }
    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    override fun listDeals() {
        view.showProgress()
        val subscription = model.getHTML(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({ html ->
                    onNext(html)
                }, {
                    onError()
                }, {
                    onComplete()
                })
        disposable.add(subscription)
    }

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun onDestroy() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }

    private fun onNext(html: String) {
        val dealsList = dealsMachine.getLatestDealsFromHTML(html)
        val title = dealsMachine.getTitle(html)
        view.loadDeals(dealsList)
        view.loadTitle(title)
    }

    private fun onComplete() {
        view.hideProgress()
        view.hideReloadFab()
    }

    private fun onError() {
        view.hideProgress()
        view.showMessage("Error!")
        view.showErrorTitle()
        view.showReloadFab()
    }
}