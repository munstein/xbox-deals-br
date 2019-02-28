package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainContract
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

/**
 * Created by @Munstein on 05/02/2018. --21:53
 */
class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    lateinit var viewMock: MainContract.View

    @Mock
    lateinit var modelMock: MainContract.Model

    @Mock
    lateinit var dealsMock: DealsMachineJsoup

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        modelMock = mock(MainContract.Model::class.java)
        viewMock = mock(MainContract.View::class.java)
        dealsMock = mock(DealsMachineJsoup::class.java)
        presenter = MainPresenter(modelMock, dealsMock)
        presenter.setView(viewMock)
    }

    @Test
    fun shouldShowDialog() {
        `when`(modelMock.getHTML("https://www.arenaxbox.com.br/tag/deals-with-gold/")).thenReturn(Flowable.just("hello"))
        presenter.listDeals()
        verify(viewMock).showProgress()
    }

    @Ignore
    @Test
    fun shouldShowErrorMsg() {
        `when`(modelMock.getHTML("https://www.arenaxbox.com.br/tag/deals-with-gold/"))
                .thenReturn(Flowable.error(Exception("error")))
        presenter.listDeals()
        Thread.sleep(2000)
        verify(viewMock).hideProgress()
    }
}