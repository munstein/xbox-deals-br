package com.munstein.xboxdealsbr.views.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.adapter.DealsAdapter
import com.munstein.xboxdealsbr.base.BaseActivity
import com.munstein.xboxdealsbr.model.Deal
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : BaseActivity(), MainMVP.view {

    private lateinit var progressDialog : MaterialDialog
    private lateinit var dealsAdapter : DealsAdapter
    private lateinit var layoutManager : RecyclerView.LayoutManager
    private val presenter : MainMVP.presenter = MainPresenter(MainModelOkHTTP(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    private fun init(){
        progressDialog = MaterialDialog.Builder(this)
                .content(R.string.dialog_loading)
                .progress(true, 0)
                .build()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        presenter.displayDeals()
    }

    override fun showDialog() {
        progressDialog.show()
    }

    override fun hideDialog() {
        this.runOnUiThread {
            progressDialog.hide()
        }
    }

    override fun loadDeals(deals : List<Deal>) {
        dealsAdapter = DealsAdapter(deals as ArrayList<Deal>)
        this.runOnUiThread {
            main_deals_recycler_view.adapter = dealsAdapter
            main_deals_recycler_view.layoutManager = layoutManager
        }
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

}
