package com.munstein.xboxdealsbr.views.main

import com.munstein.xboxdealsbr.model.Deal
import okhttp3.Callback

/**
 * Created by @Munstein on 21/01/2018. --22:12
 */

interface MainMVP {

    interface model{
        fun getHTML(call : Callback)
    }

    interface view{
        fun showDialog()
        fun hideDialog()
        fun showMessage(msg : String)
        fun loadDeals(deals : List<Deal>)
    }

    interface presenter{
        fun displayDeals()
    }
}