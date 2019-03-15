package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainModelOkHTTP
import com.munstein.xboxdealsbr.app.main.url.IMainUrlProvider
import com.munstein.xboxdealsbr.url.AppUrls
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by @Munstein on 09/03/2018. --02:35
 */
class MainModelTest {

    @Mock
    lateinit var mainUrlProvider: IMainUrlProvider

    private lateinit var mainModelOkHTTP: MainModelOkHTTP
    private val testObserver = TestSubscriber<String>()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mainUrlProvider.getMainUrl())
                .thenReturn(AppUrls.mainUrl)
        mainModelOkHTTP = MainModelOkHTTP(mainUrlProvider)
    }

    @Test
    fun shoudlAssertComplete() {
        mainModelOkHTTP.getHTML()
                .subscribe(testObserver)
        testObserver.assertComplete()
    }

    @Test
    fun shoudlContainHTML_Information() {
        mainModelOkHTTP.getHTML()
                .subscribe(testObserver)
        val html = testObserver.values()[0]
        Assert.assertTrue(html.contains("<html"))
    }
}