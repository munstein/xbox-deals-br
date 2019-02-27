package com.munstein.xboxdealsbr.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by @Munstein on 23/01/2018. --23:35
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}