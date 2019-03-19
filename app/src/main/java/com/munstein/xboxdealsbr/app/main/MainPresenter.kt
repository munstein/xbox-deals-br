package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.core.IDealsMachine
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 30/01/2018. --17:24
 */

class MainPresenter(private val model: IMainContract.Model, dealsMachineJsoup: IDealsMachine) : IMainContract.Presenter {

    private lateinit var view: IMainContract.View
    private val dealsMachine: IDealsMachine = dealsMachineJsoup
    private val disposable by lazy { CompositeDisposable() }

    override fun listDeals() {
        view.hideErrorTitle()
        view.showProgress()
        val subscription = model.getHTML()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({ html ->
                    scrapHtml(html)
                }, {
                    showError()
                }, {
                    hideLoadingAndFab()
                })
        disposable.add(subscription)
    }

    override fun setView(view: IMainContract.View) {
        this.view = view
    }

    override fun onDestroy() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }

    private fun scrapHtml(html: String) {
        val dealsList = dealsMachine.getLatestDealsFromHTML(html)
        val title = dealsMachine.getTitle(html)
        view.loadDeals(dealsList)
        view.loadTitle(title)
    }

    private fun hideLoadingAndFab() {
        view.hideProgress()
        view.hideReloadFab()
    }

    private fun showError() {
        view.hideProgress()
        view.showMessage("Error!")
        view.showErrorTitle()
        view.showReloadFab()
    }
}