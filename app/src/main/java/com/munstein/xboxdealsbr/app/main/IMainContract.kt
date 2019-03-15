package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.model.Deal
import io.reactivex.Flowable

/**
 * Created by @Munstein on 21/01/2018. --22:12
 */

interface IMainContract {

    interface Model {
        fun getHTML(): Flowable<String>
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showReloadFab()
        fun hideReloadFab()
        fun showMessage(msg: String)
        fun loadDeals(deals: List<Deal>)
        fun loadTitle(title: String)
        fun showErrorTitle()
        fun hideErrorTitle()
    }

    interface Presenter {
        fun listDeals()
        fun setView(view: View)
        fun onDestroy()
    }
}