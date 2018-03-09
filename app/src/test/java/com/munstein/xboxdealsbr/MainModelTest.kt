package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import io.reactivex.observers.TestObserver
import org.junit.Test
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert
import org.junit.Assert.assertThat
import org.junit.Before


/**
 * Created by @Munstein on 09/03/2018. --02:35
 */
class MainModelTest {

    val mainModelOkHTTP = MainModelOkHTTP()
    val testObserver = TestObserver<String>()
    val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"


    @Test
    fun shoudlAssertComplete(){
        mainModelOkHTTP.getHTML(url)
                .subscribe(testObserver)
        testObserver.assertComplete()
    }

    @Test
    fun shoudlContainHTML_Information(){
        mainModelOkHTTP.getHTML(url)
                .subscribe(testObserver)
        val html = testObserver.values()[0]
        Assert.assertTrue(html.contains("<html"))
    }
}