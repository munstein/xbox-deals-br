package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.IMainContract
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

/**
 * Created by @Munstein on 05/02/2018. --21:53
 */
class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    lateinit var viewMock: IMainContract.View

    @Mock
    lateinit var modelMock: IMainContract.Model

    @Mock
    lateinit var dealsMock: DealsMachineJsoup

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(modelMock, dealsMock)
        presenter.setView(viewMock)
    }

    @Test
    fun shouldShowDialog() {
        `when`(modelMock.getHTML()).thenReturn(Flowable.just("hello"))
        presenter.listDeals()
        verify(viewMock).showProgress()
    }

    @Test
    fun shouldShowErrorMsg() {
        `when`(modelMock.getHTML())
                .thenReturn(Flowable.error(Exception("error")))
        presenter.listDeals()
        Thread.sleep(2000)
        verify(viewMock).hideProgress()
    }
}