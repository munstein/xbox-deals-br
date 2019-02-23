package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.core.DealsMachineJsoup
import junit.framework.Assert
import org.junit.Test

/**
 * Created by @Munstein on 22/01/2018. --21:16
 */
class DealsMachineJsoupTest {

    private val dealsMachine by lazy { DealsMachineJsoup() }

    @Test
    fun testGetDeals() {
        val url = "https://www.arenaxbox.com.br/tag/deals-with-gold/"
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isNotEmpty())
    }

    @Test
    fun testGetDealsPassingEmptyHTML() {
        val html = ""
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }

    @Test
    fun testGetDealsPassingEmptyURL() {
        val url = ""
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingInvalidURL() {
        val url = "not a valid url"
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingSenselessURL() {
        val url = "http://google.com"
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromURL(url).isEmpty())
    }

    @Test
    fun testGetDealsPassingCorruptedHTML() {
        val html = "this is not a valid html"
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }

    @Test
    fun testGetDealsPassingIncompletedHTML() {
        val html = "<html></html>"
        Assert.assertEquals(true, dealsMachine.getLatestDealsFromHTML(html).isEmpty())
    }
}