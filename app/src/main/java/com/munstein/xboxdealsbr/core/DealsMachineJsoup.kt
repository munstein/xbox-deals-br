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
        return if (html.isNotEmpty()) {
            val doc = Jsoup.parse(html)
            val url = getLatestDealsURL(doc)
            val elements = getDealsTables(url)
            getDealsFromTables(elements)
        } else {
            ArrayList()
        }
    }

    override fun getLatestDealsFromURL(baseUrl: String): List<Deal> {
        return try {
            val doc = Jsoup.connect(baseUrl).get()
            val url = getLatestDealsURL(doc)
            val elements = getDealsTables(url)
            getDealsFromTables(elements)
        } catch (exception: Exception) {
            ArrayList()
        }
    }

    override fun getTitle(html: String): String {
        return try {
            val doc = Jsoup.parse(html)
            val title = doc.select(".post-title").first {
                it.text().contains("with Gold")
            }
            title.text()
        } catch (exception: Exception) {
            ""
        }
    }

    private fun getLatestDealsURL(doc: Document): String {
        return try {
            val elements = doc.select("article")
            val h2 = elements[0].select(".title")
            val a = h2[0].select("a")
            a.attr("href")
        } catch (exception: Exception) {
            ""
        }
    }

    private fun getDealsTables(url: String): Elements {
        return if (url.isNotEmpty()) {
            val dealsPageDocument = Jsoup.connect(url).get()
            val div = dealsPageDocument.select(".entry-content")
            div.select("table")
        } else {
            Elements()
        }
    }

    private fun getDealsFromTables(tables: Elements): List<Deal> {
        if (tables.size > 0) {
            val deals = ArrayList<Deal>()
            for (i in 0 until tables.size) {
                val tableContents = tables[i].select("tr")
                for (x in 2 until tableContents.size) {
                    val tds = tableContents[x].select("td")
                    val game = tds[0].text()
                    val type = tds[1].text()
                    val url = tds[0].select("a").attr("href")
                    val discount = tds[2].text()
                    val value = tds[3].text()
                    deals.add(Deal(game, type, discount, value, url))
                }
                deals.sortBy { it.game }
                return deals
            }
        }
        return ArrayList()
    }
}