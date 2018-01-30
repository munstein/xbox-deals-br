package com.munstein.xboxdealsbr.views.main

import com.munstein.xboxdealsbr.services.DealsService
import retrofit2.Callback

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModel {

    fun getHTML(callback : Callback<String>) {
        var dealsService = DealsService()
        var call = dealsService.buildService().getURLHTML()
        call.enqueue(callback)
    }

}