package com.munstein.xboxdealsbr.views.main

import com.munstein.xboxdealsbr.model.Deal

/**
 * Created by @Munstein on 21/01/2018. --22:12
 */
interface MainMVP {

    interface model{
        fun getDeals() : List<Deal>
    }

    interface view{
        fun showDialog()
        fun hideDialog()
        fun loadDeals()
    }

    interface presenter{
        fun displayDeals()
    }
}