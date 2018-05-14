package com.munstein.xboxdealsbr.core

import com.munstein.xboxdealsbr.model.Deal
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by @Munstein on 21/01/2018. --22:53
 */

open class DealsMachineJsoup : IDealsMachine {

    override fun getLatestDealsFromHTML(html: String): List<Deal> {
        if (html.length > 0) {
            val doc = Jsoup.parse(html)
            val url = getLatestDealsURL(doc)
            val elements = getDealsTables(url)
            return getDealsFromTables(elements)
        } else {
            return ArrayList()
        }
    }

    override fun getLatestDealsFromURL(baseUrl: String): List<Deal> {
        try {
            val doc = Jsoup.connect(baseUrl).get()
            val url = getLatestDealsURL(doc)
            val elements = getDealsTables(url)
            return getDealsFromTables(elements)
        } catch (exception: Exception) {
            return ArrayList()
        }
    }

    override fun getTitle(html: String): String {
        try {
            val doc = Jsoup.parse(html)
            val title = doc.select(".post-title").filter({
                it.text().contains("with Gold")
            }).first()
            return title.text()
        } catch (exception: Exception) {
            return ""
        }
    }

    private fun getLatestDealsURL(doc: Document): String {
        try {
            val elements = doc.select("article")
            val h2 = elements.get(0).select(".title")
            val a = h2.get(0).select("a")
            val url = a.attr("href")
            return url
        } catch (exception: Exception) {
            return ""
        }
    }

    private fun getDealsTables(url: String): Elements {
        if (url.length > 0) {
            val dealsPageDocument = Jsoup.connect(url).get()
            val div = dealsPageDocument.select(".entry-content")
            val tables = div.select("table")
            return tables
        } else {
            return Elements()
        }
    }

    private fun getDealsFromTables(tables: Elements): List<Deal> {
        if (tables.size > 0) {
            val deals = ArrayList<Deal>()
            for (i in 0..tables.size - 1) {
                val tableContents = tables[i].select("tr")
                if (tableContents.select("td")[0].text().contains("Xbox One"))
                    for (x in 2 .. tableContents.size - 1) {
                        val tds = tableContents[x].select("td")
                        val game = tds[0].text()
                        val type = tds[1].text()
                        val url = tds[0].select("a").attr("href")
                        val discount = tds[2].text()
                        val value = tds[3].text()
                        deals.add(Deal(game, type, discount, value, url))
                    }
            }
            deals.sortBy { it.game }
            return deals
        } else {
            return ArrayList()
        }
    }
}