package com.munstein.xboxdealsbr.app.main.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.munstein.xboxdealsbr.R
import com.munstein.xboxdealsbr.model.Deal
import com.munstein.xboxdealsbr.util.CustomTabsIntentUtil
import kotlinx.android.synthetic.main.item_deal.view.*

class DealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val txtGame: TextView = itemView.deal_txt_title
    private val txtType: TextView = itemView.deal_txt_type
    private val txtDiscount: TextView = itemView.deal_txt_discount
    private val txtValue: TextView = itemView.deal_txt_value
    private val viewBottomDecoration: View = itemView.deal_view_bottom_decoration
    private val root: CardView = itemView.deal_root_cardview

    fun bind(deal: Deal) {
        with(deal) {
            txtGame.text = game
            txtType.text = type
            viewBottomDecoration.background = when (type) {
                Deal.TYPE_COMP -> itemView.context.getDrawable(R.color.comp)
                else -> itemView.context.getDrawable(R.color.colorAccent)
            }
            txtDiscount.text = discount
            txtValue.text = value
            root.setOnClickListener {
                CustomTabsIntentUtil.launch(url, txtGame.context)
            }
        }
    }
}