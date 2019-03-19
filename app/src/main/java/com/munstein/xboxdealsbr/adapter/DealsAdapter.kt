package com.munstein.xboxdealsbr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.model.Deal
import com.munstein.xboxdealsbr.util.CustomTabsIntentUtil
import kotlinx.android.synthetic.main.holder_deal.view.*

/**
 * Created by @Munstein on 25/01/2018. --14:55
 */

class DealsAdapter(private val deals: ArrayList<Deal>) : RecyclerView.Adapter<DealsAdapter.DealsHolder>() {
    override fun onBindViewHolder(holder: DealsHolder, position: Int) {
        val deal = deals[position]
        with(deal) {
            holder.txtGame.text = game
            holder.txtType.text = type
            holder.txtDiscount.text = discount
            holder.txtValue.text = value
            holder.root.setOnClickListener {
                CustomTabsIntentUtil.launch(url, holder.txtGame.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DealsHolder {

        return DealsHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_deal, parent, false))
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    class DealsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGame: TextView = itemView.deal_txt_title
        val txtType: TextView = itemView.deal_txt_type
        val txtDiscount: TextView = itemView.deal_txt_discount
        val txtValue: TextView = itemView.deal_txt_value
        val root: CardView = itemView.deal_root_cardview
    }
}