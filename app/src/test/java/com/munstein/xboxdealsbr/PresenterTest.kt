package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.modules.main.MainMVP
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit


/**
 * Created by @Munstein on 05/02/2018. --21:53
 */
class PresenterTest{

    @Mock
    internal var viewMock: MainMVP.view? = null

    @Mock
    internal var modelMock: MainMVP.model? = null

    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup(){
        modelMock = mock(MainMVP.model::class.java)
        viewMock = mock(MainMVP.view::class.java)
    }

}