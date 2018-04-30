package com.munstein.xboxdealsbr.app.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.adapter.DealsAdapter
import com.munstein.xboxdealsbr.base.BaseActivity
import com.munstein.xboxdealsbr.base.BaseApp
import com.munstein.xboxdealsbr.model.Deal
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.view {

    private lateinit var progressDialog: MaterialDialog
    private lateinit var dealsAdapter: DealsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var presenter: MainContract.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).getComponent().inject(this)
        presenter.setView(this)
        init()
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    private fun init() {
        progressDialog = MaterialDialog.Builder(this)
                .content(R.string.dialog_loading)
                .progress(true, 0)
                .build()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        presenter.displayDeals()
        main_btn_reload.setOnClickListener({presenter.displayDeals()})
    }

    override fun showDialog() {
        this.runOnUiThread {
            progressDialog.show()
        }
    }

    override fun hideDialog() {
        this.runOnUiThread {
            progressDialog.dismiss()
        }
    }

    override fun loadDeals(deals: List<Deal>) {
        dealsAdapter = DealsAdapter(deals as ArrayList<Deal>)
        val dividerItemDecoration = DividerItemDecoration(main_deals_recycler_view.context,
                layoutManager.orientation)
        this.runOnUiThread {
            main_deals_recycler_view.adapter = dealsAdapter
            main_deals_recycler_view.layoutManager = layoutManager
            main_deals_recycler_view.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun showMessage(msg: String) {
        this.runOnUiThread {
            showToast(msg)
        }
    }

    override fun loadTitle(title: String) {
        this.runOnUiThread {
            main_txt_title.text = title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun hideButton() {
        this.runOnUiThread {
            main_btn_reload.visibility = View.GONE
        }
    }

    override fun showButton() {
        this.runOnUiThread {
            main_btn_reload.visibility = View.VISIBLE
        }
    }

    override fun showErrorTitle() {
        this.runOnUiThread{
        main_txt_title.text = getString(R.string.error_msg)
        }
    }

}
