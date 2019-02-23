package com.munstein.xboxdealsbr.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.model.Deal
import android.graphics.Typeface

/**
 * Created by @Munstein on 25/01/2018. --14:55
 */

class DealsAdapter(private var deals: ArrayList<Deal>) : RecyclerView.Adapter<DealsAdapter.DealsHolder>() {
    override fun onBindViewHolder(holder: DealsHolder, position: Int) {
        val deal = deals[position]
        with(deal){
            holder.txtGame.text = game
            holder.txtType.text = type
            holder.txtDiscount.text = discount
            holder.txtValue.text = value
            holder.txtGame.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                holder.txtGame.context.startActivity(browserIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DealsHolder {
        val view = DealsHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_deal, parent, false))

        return view
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    class DealsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtGame: TextView = itemView.findViewById(R.id.deal_txt_title)
        val txtType: TextView = itemView.findViewById(R.id.deal_txt_type)
        val txtDiscount: TextView = itemView.findViewById(R.id.deal_txt_discount)
        val txtValue: TextView = itemView.findViewById(R.id.deal_txt_value)

    }

}