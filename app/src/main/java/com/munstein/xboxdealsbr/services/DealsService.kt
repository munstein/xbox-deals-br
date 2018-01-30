package com.munstein.xboxdealsbr.services

import retrofit2.http.GET

/**
 * Created by @Munstein on 30/01/2018. --17:17
 */
interface DealsService {
    @GET("")
    fun getURLHTML() : String
}