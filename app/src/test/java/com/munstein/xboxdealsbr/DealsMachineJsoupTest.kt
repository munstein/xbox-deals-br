package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import junit.framework.Assert
import org.junit.Test

/**
 * Created by @Munstein on 22/01/2018. --21:16
 */
class DealsMachineJsoupTest {

    @Test
    fun testGetDeals(){
        val url: String = "https://www.arenaxbox.com.br/tag/deals-with-gold/"
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isNotEmpty())
    }

}