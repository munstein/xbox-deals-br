package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.views.main.MainMVP
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule



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

}