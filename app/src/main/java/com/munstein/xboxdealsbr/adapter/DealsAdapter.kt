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

class DealsAdapter : RecyclerView.Adapter<DealsAdapter.DealsHolder> {

    private var deals: ArrayList<Deal>

    constructor(deals: ArrayList<Deal>) {
        this.deals = deals
    }

    override fun onBindViewHolder(holder: DealsHolder?, position: Int) {
        var deal = deals[position]
        with(deal){
            holder!!.txtGame.setText(game)
            holder!!.txtType.setText(type)
            holder!!.txtDiscount.setText(discount)
            holder!!.txtValue.setText(value)
            holder!!.txtGame.setOnClickListener({
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                holder!!.txtGame.context.startActivity(browserIntent)
            })
        }

    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DealsHolder {
        var view = DealsHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.holder_deal, parent, false))
        var font = Typeface.createFromAsset(parent.context.getAssets(),
                "fonts/X360.ttf")

        view.txtGame.typeface = font
        view.txtType.typeface= font
        view.txtDiscount.typeface = font
        view.txtValue.typeface = font

        return view
    }

    class DealsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtGame: TextView = itemView.findViewById(R.id.deal_txt_title)
        val txtType: TextView = itemView.findViewById(R.id.deal_txt_type)
        val txtDiscount: TextView = itemView.findViewById(R.id.deal_txt_discount)
        val txtValue: TextView = itemView.findViewById(R.id.deal_txt_value)

    }

}