package com.munstein.xboxdealsbr

import com.munstein.xboxdealsbr.model.Deal
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * Created by @Munstein on 14/01/2018. --22:52
 */

class JsoupTests {


    private val contentTestUrl = "https://www.arenaxbox.com.br/deals-with-gold-e-ofertas-especiais-16-22-01/"
    private val baseTestUrl = "https://www.arenaxbox.com.br/tag/deals-with-gold/"
    private lateinit var doc: Document


    @Before
    fun init() {
        doc = Jsoup.connect(baseTestUrl).get()
    }

    @Test
    fun basicTest() {
        Assert.assertEquals("Deals with Gold - Arena Xbox", doc.title())
    }

    @Test
    fun getLatestDealsLink() {
        val elements = doc.select("article")
        val h2 = elements.get(0).select(".title")
        val a = h2.get(0).select("a")
        val url = a.attr("href")
        Assert.assertEquals(true, url.contains("deals-with-gold"))
    }

    @Test
    fun getTablesFromLink() {
        val dealsPageDocument = Jsoup.connect(contentTestUrl).get()
        val div = dealsPageDocument.select(".entry-content")
        val tables = div.select("table")
        Assert.assertEquals(4, tables.size)
    }

    @Test
    fun getDealsFromTables() {
        val dealsPageDocument = Jsoup.connect(contentTestUrl).get()
        val div = dealsPageDocument.select(".entry-content")
        val tables = div.select("table")
        val tableContents = tables[0].select("tr")
        val deals = ArrayList<Deal>()
        for (i in 2 until tableContents.size) {
            val tds = tableContents[i].select("td")
            val game = tds[0].text()
            val type = tds[1].text()
            val discount = tds[2].text()
            val value = tds[3].text()
            deals.add(Deal(game, type, discount, value, ""))
        }
        Assert.assertEquals(deals.size, tableContents.size - 2)
    }

    @Test
    fun getTitleTest() {
        val dealsPageDocument = Jsoup.connect(contentTestUrl).get()
        val title = dealsPageDocument.title()
        Assert.assertEquals("Deals with Gold e Ofertas Especiais – 16 a 22/01 - Arena Xbox", title)
    }
}