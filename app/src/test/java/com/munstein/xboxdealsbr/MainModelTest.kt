package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert
import org.junit.Test

/**
 * Created by @Munstein on 09/03/2018. --02:35
 */
class MainModelTest {

    private val mainModelOkHTTP = MainModelOkHTTP()
    private val testObserver = TestSubscriber <String>()
    private val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"

    @Test
    fun shoudlAssertComplete() {
        mainModelOkHTTP.getHTML(url)
                .subscribe(testObserver)
        testObserver.assertComplete()
    }

    @Test
    fun shoudlContainHTML_Information() {
        mainModelOkHTTP.getHTML(url)
                .subscribe(testObserver)
        val html = testObserver.values()[0]
        Assert.assertTrue(html.contains("<html"))
    }
}