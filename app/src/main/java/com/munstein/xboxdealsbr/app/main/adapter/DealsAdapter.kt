package com.munstein.xboxdealsbr.app.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.model.Deal
import com.munstein.xboxdealsbr.util.CustomTabsIntentUtil
import kotlinx.android.synthetic.main.item_deal.view.*

/**
 * Created by @Munstein on 25/01/2018. --14:55
 */

class DealsAdapter(private val deals: ArrayList<Deal>) : RecyclerView.Adapter<DealsViewHolder>() {
    override fun onBindViewHolder(viewHolder: DealsViewHolder, position: Int) {
        viewHolder.bind(deals[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DealsViewHolder {

        return DealsViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_deal, parent, false))
    }

    override fun getItemCount(): Int {
        return deals.size
    }
}