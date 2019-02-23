package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.model.Deal
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by @Munstein on 21/01/2018. --22:12
 */

interface MainContract {

    interface model {
        fun getHTML(url: String): Flowable<String>
    }

    interface view {
        fun showProgress()
        fun hideProgress()
        fun showReloadFab()
        fun hideReloadFab()
        fun showMessage(msg: String)
        fun loadDeals(deals: List<Deal>)
        fun loadTitle(title: String)
        fun showErrorTitle()
    }

    interface presenter {
        fun displayDeals()
        fun setView(view: view)
        fun onDestroy()
    }
}