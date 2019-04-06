package com.munstein.xboxdealsbr.app.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.app.main.adapter.DealsAdapter
import com.munstein.xboxdealsbr.base.BaseActivity
import com.munstein.xboxdealsbr.base.BaseApp
import com.munstein.xboxdealsbr.model.Deal
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), IMainContract.View {

    private lateinit var dealsAdapter: DealsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var presenter: IMainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).getComponent().inject(this)
        init()
    }

    private fun init() {
        setupRecycleView()
        setupEvents()
        setupAppBar()
        setupPresenter()
    }

    private fun setupPresenter() {
        presenter.setView(this)
        presenter.listDeals()
    }

    private fun setupRecycleView() {
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall_down)
        main_deals_recycler_view.layoutAnimation = animation
        main_deals_recycler_view.layoutManager = layoutManager
    }

    private fun setupAppBar() {
        supportActionBar?.elevation = 0f
    }

    private fun setupEvents() {
        main_fab_reload.setOnClickListener { presenter.listDeals() }
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
            dealsAdapter = DealsAdapter(ArrayList(deals))
            main_deals_recycler_view.visibility = VISIBLE
            main_deals_recycler_view.adapter = dealsAdapter
        }
    }

    override fun showMessage(msg: String) {
        runOnUiThread {
            Snackbar.make(main_root, msg, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun loadTitle(title: String) {
        runOnUiThread {
            main_txt_subtitle.text = title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun hideReloadFab() {
        runOnUiThread {
            main_fab_reload.hide()
        }
    }

    override fun showReloadFab() {
        runOnUiThread {
            main_fab_reload.show()
        }
    }

    override fun showErrorTitle() {
        runOnUiThread {
            main_txt_subtitle.text = getString(R.string.error_msg)
        }
    }

    override fun hideErrorTitle() {
        runOnUiThread {
            main_txt_subtitle.text = ""
        }
    }
}
