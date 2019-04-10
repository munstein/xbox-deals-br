package com.munstein.xboxdealsbr.model

/**
 * Created by @Munstein on 17/01/2018. --23:33
 */
data class Deal(
    val game: String,
    val type: String,
    val discount: String,
    val value: String,
    val url: String
){
    companion object {
        const val TYPE_GAME = "Jogo"
        const val TYPE_COMP = "Comp."
    }
}