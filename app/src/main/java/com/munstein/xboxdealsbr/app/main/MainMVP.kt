package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.model.Deal
import io.reactivex.Observable
import okhttp3.Callback
import okhttp3.Response

/**
 * Created by @Munstein on 21/01/2018. --22:12
 */

interface MainMVP {

    interface model {
        fun getHTML(url : String) : Observable<String>
    }

    interface view {
        fun showDialog()
        fun hideDialog()
        fun showMessage(msg: String)
        fun loadDeals(deals: List<Deal>)
        fun loadTitle(title: String)
    }

    interface presenter {
        fun displayDeals()
        fun setView(view : MainMVP.view)
        fun onPause()
    }
}