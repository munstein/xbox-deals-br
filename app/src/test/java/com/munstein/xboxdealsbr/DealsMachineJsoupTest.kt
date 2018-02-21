package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import com.munstein.xboxdealsbr.model.Deal
import junit.framework.Assert
import org.jsoup.Jsoup
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

    @Test
    fun testGetDealsPassingEmptyHTML(){
        val html: String = ""
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }

    @Test
    fun testGetDealsPassingEmptyURL(){
        val url: String = ""
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingInvalidURL(){
        val url: String = "not a valid url"
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingSenselessURL(){
        val url: String = "http://google.com"
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingCorruptedHTML(){
        val html: String = "this is not a valid html"
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }

    @Test
    fun testGetDealsPassingIncompletedHTML(){
        val html: String = "<html></html>"
        var dealsMachine = DealsMachineJsoup()
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }

}