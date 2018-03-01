package com.munstein.xboxdealsbr.core

import com.munstein.xboxdealsbr.model.Deal

/**
 * Created by @Munstein on 12/02/2018. --00:32
 */
interface IDealsMachine {
    fun getLatestDealsFromHTML(html: String): List<Deal>
    fun getLatestDealsFromURL(baseUrl: String): List<Deal>
    fun getTitle(html: String): String
}