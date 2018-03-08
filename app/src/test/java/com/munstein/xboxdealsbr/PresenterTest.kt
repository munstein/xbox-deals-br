package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.app.main.MainMVP
import com.munstein.xboxdealsbr.app.main.MainPresenter
import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit


/**
 * Created by @Munstein on 05/02/2018. --21:53
 */
class PresenterTest{

    lateinit var presenter : MainPresenter

    @Mock
    lateinit var viewMock: MainMVP.view

    @Mock
    lateinit var modelMock: MainMVP.model

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup(){
        modelMock = mock(MainMVP.model::class.java)
        viewMock = mock(MainMVP.view::class.java)
        presenter = MainPresenter(modelMock, DealsMachineJsoup())
        presenter.setView(viewMock)
    }

    @Test
    fun shouldShowDialog(){
        `when`(modelMock!!.getHTML()).thenReturn(Observable.just("hello"))
        presenter.displayDeals()
        verify(viewMock, times(1)).showDialog()
    }

    @Test
    fun shouldGetHtml(){
        `when`(modelMock!!.getHTML()).thenReturn(Observable.just("world"))
        presenter.displayDeals()
        verify(modelMock, times(1)).getHTML()
    }


}