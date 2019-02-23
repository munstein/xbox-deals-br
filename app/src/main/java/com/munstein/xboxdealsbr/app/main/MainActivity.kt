package com.munstein.xboxdealsbr.app.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.adapter.DealsAdapter
import com.munstein.xboxdealsbr.base.BaseActivity
import com.munstein.xboxdealsbr.base.BaseApp
import com.munstein.xboxdealsbr.model.Deal
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.view {

    private lateinit var dealsAdapter: DealsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var presenter: MainContract.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.elevation = 0f
        (application as BaseApp).getComponent().inject(this)
        presenter.setView(this)
        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        presenter.displayDeals()
        main_fab_reload.setOnClickListener { presenter.displayDeals() }
    }

    override fun showProgress() {
        this.runOnUiThread {
            main_progress.visibility = VISIBLE
        }
    }

    override fun hideProgress() {
        this.runOnUiThread {
            main_progress.visibility = GONE
        }
    }

    override fun loadDeals(deals: List<Deal>) {
        runOnUiThread {
            main_deals_recycler_view.visibility = VISIBLE
            dealsAdapter = DealsAdapter(deals as ArrayList<Deal>)
            val dividerItemDecoration = DividerItemDecoration(main_deals_recycler_view.context,
                    layoutManager.orientation)
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

    override fun hideReloadFab() {
        this.runOnUiThread {
            main_fab_reload.show()
        }
    }

    override fun showReloadFab() {
        this.runOnUiThread {
            main_fab_reload.hide()
        }
    }

    override fun showErrorTitle() {
        this.runOnUiThread {
            main_txt_title.text = getString(R.string.error_msg)
        }
    }

}
