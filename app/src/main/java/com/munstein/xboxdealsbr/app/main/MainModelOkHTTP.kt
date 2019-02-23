package com.munstein.xboxdealsbr.app.main

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Flowable.create
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModelOkHTTP : MainContract.model {

    override fun getHTML(url: String): Flowable<String> {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        return create({
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    it.onNext(response.body()!!.string())
                }

            } catch (exception: Exception) {
                it.onError(exception)
            } finally {
                it.onComplete()
            }
        }, BackpressureStrategy.LATEST)
    }

}