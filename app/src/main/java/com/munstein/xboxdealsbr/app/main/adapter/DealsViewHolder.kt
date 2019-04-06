package com.munstein.xboxdealsbr.app.main.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.munstein.xboxdealsbr.model.Deal
import com.munstein.xboxdealsbr.util.CustomTabsIntentUtil
import kotlinx.android.synthetic.main.item_deal.view.*

class DealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtGame: TextView = itemView.deal_txt_title
    val txtType: TextView = itemView.deal_txt_type
    val txtDiscount: TextView = itemView.deal_txt_discount
    val txtValue: TextView = itemView.deal_txt_value
    val root: CardView = itemView.deal_root_cardview

    fun bind(deal: Deal) {
        with(deal) {
            txtGame.text = game
            txtType.text = type
            txtDiscount.text = discount
            txtValue.text = value
            root.setOnClickListener {
                CustomTabsIntentUtil.launch(url, txtGame.context)
            }
        }
    }
}