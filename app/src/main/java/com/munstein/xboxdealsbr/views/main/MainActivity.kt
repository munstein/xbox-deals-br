package com.munstein.xboxdealsbr.views.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.base.BaseActivity
import com.munstein.xboxdealsbr.model.Deal
import okhttp3.*
import java.io.IOException

class MainActivity : BaseActivity(), MainMVP.view {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    override fun showDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDeals(deals : List<Deal>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

}
