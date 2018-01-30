package com.munstein.xboxdealsbr.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Created by @Munstein on 30/01/2018. --17:17
 */
class DealsService {
    interface getAPI{
        @GET("")
        fun getURLHTML() : Call<String>
    }

    fun buildService() : getAPI{
        val retrofit = Retrofit.Builder().baseUrl("").build()
        return retrofit.create(getAPI::class.java)
    }
}