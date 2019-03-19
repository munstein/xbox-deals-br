package com.munstein.xboxdealsbr.app.main

import com.munstein.xboxdealsbr.app.main.url.IMainUrlProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Flowable.create
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by @Munstein on 30/01/2018. --17:35
 */
class MainModelOkHTTP(private val mainUrlProvider: IMainUrlProvider) : IMainContract.Model {

    override fun getHTML(): Flowable<String> {
        val client = OkHttpClient()
        val request = Request.Builder().url(mainUrlProvider.getMainUrl()).build()
        return create({ emitter ->
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emitter.onNext(body.string())
                    }
                }
            } catch (exception: Exception) {
                emitter.onError(exception)
            } finally {
                emitter.onComplete()
            }
        }, BackpressureStrategy.LATEST)
    }
}