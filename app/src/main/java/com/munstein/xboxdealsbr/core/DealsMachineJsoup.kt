package com.munstein.xboxdealsbr.core

import com.munstein.xboxdealsbr.model.Deal
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by @Munstein on 21/01/2018. --22:53
 */

class DealsMachineJsoup : IDealsMachine {

    override fun getLatestDealsFromHTML(html: String): List<Deal> {
        if (html.length > 0) {
            var doc = Jsoup.parse(html)
            var url = getLatestDealsURL(doc)
            var elements = getDealsTables(url)
            return getDealsFromTables(elements)
        } else {
            return ArrayList<Deal>()
        }
    }

    override fun getLatestDealsFromURL(baseUrl: String): List<Deal> {
        try {
            var doc = Jsoup.connect(baseUrl).get()
            var url = getLatestDealsURL(doc)
            var elements = getDealsTables(url)
            return getDealsFromTables(elements)
        } catch (exception: Exception) {
            return ArrayList<Deal>()
        }
    }

    override fun getTitle(html: String): String {
        try {
            var doc = Jsoup.parse(html)
            var title = doc.select(".post-title")
            return title[0].text()
        } catch (exception: Exception) {
            return ""
        }
    }

    private fun getLatestDealsURL(doc: Document): String {
        try {
            var elements = doc.select("article")
            var h2 = elements.get(0).select(".title")
            var a = h2.get(0).select("a")
            var url = a.attr("href")
            return url
        } catch (exception: Exception) {
            return ""
        }
    }

    private fun getDealsTables(url: String): Elements {
        if (url.length > 0) {
            var dealsPageDocument = Jsoup.connect(url).get()
            var div = dealsPageDocument.select(".entry-content")
            var tables = div.select("table")
            return tables
        } else {
            return Elements()
        }
    }

    private fun getDealsFromTables(tables: Elements): List<Deal> {
        if (tables.size > 0) {
            var tableContents = tables[0].select("tr")
            var deals = ArrayList<Deal>()
            for (i in 2..tableContents.size - 1) {
                var tds = tableContents[i].select("td")
                var game = tds[0].text()
                var type = tds[1].text()
                var url = tds[0].select("a").attr("href")
                var discount = tds[2].text()
                var value = tds[3].text()
                deals.add(Deal(game, type, discount, value, url))
            }
            return deals
        } else {
            return ArrayList<Deal>()
        }
    }


}