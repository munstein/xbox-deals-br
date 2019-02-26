package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainContract
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit

/**
 * Created by @Munstein on 05/02/2018. --21:53
 */
class MainPresenterTest {

    lateinit var presenter: MainPresenter

    @Mock
    lateinit var viewMock: MainContract.view

    @Mock
    lateinit var modelMock: MainContract.model

    @Mock
    lateinit var dealsMock: DealsMachineJsoup

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        modelMock = mock(MainContract.model::class.java)
        viewMock = mock(MainContract.view::class.java)
        dealsMock = mock(DealsMachineJsoup::class.java)
        presenter = MainPresenter(modelMock, dealsMock)
        presenter.setView(viewMock)
    }

    @Test
    fun shouldShowDialog() {
        `when`(modelMock.getHTML("https://www.arenaxbox.com.br/tag/deals-with-gold/")).thenReturn(Flowable.just("hello"))
        presenter.listDeals()
        verify(viewMock, times(1)).showProgress()
    }

    @Test
    fun shouldShowErrorMsg() {
        `when`(modelMock.getHTML("https://www.arenaxbox.com.br/tag/deals-with-gold/"))
                .thenReturn(Flowable.error(Exception("error")))
        presenter.listDeals()
        verify(viewMock, times(1)).showErrorTitle()
    }
}