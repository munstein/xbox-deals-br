package com.munstein.xboxdealsbr

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by @Munstein on 14/01/2018. --22:52
 */
class JsoupTests {

    private lateinit var doc : Document

    @Before
    fun init(){
        doc = Jsoup.connect("https://www.arenaxbox.com.br/tag/deals-with-gold/").get()
    }

    @Test
    fun basicTest(){
        Assert.assertEquals("Deals with Gold - Arena Xbox", doc.title())
    }

    @Test
    fun getTenLatestDealsArticles(){
        var elements = doc.select(".listing-blog-4" )
        Assert.assertEquals(10, elements.get(0).childNodeSize())
    }

    @Test
    fun getLatestDealsLink(){
        var elements = doc.select("article" )
        var h2 = elements.get(0).select(".title")
        var a = h2.get(0).select("a")
        var href = a.get(0)
        var url = href.attr("href")
        Assert.assertEquals("https://www.arenaxbox.com.br/deals-with-gold-e-ofertas-especiais-09-15-01/", url)
    }
}