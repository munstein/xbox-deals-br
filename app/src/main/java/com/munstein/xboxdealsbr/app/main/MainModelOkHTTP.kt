package com.munstein.xboxdealsbr.app.main

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModelOkHTTP : MainMVP.model {

    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    override fun getHTML() : Observable<String> {
        var client = OkHttpClient()
        var request = Request.Builder().url(url).build()
        return Observable.create({
            try{
                var response = client.newCall(request).execute()
                if(response.isSuccessful){
                    it.onNext(response.body()!!.string())
                }

            }catch (exception : Exception){
                it.onError(exception)
            }finally {
                it.onComplete()
            }
        })
    }

}