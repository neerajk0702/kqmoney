package com.kredivation.kqmoney.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.row_grid.view.*
import java.util.*


class CustomGridViewAdapter : BaseAdapter {
    var itemList = ArrayList<News>()
    var context: Context? = null

    constructor(context: Context, itemlist: ArrayList<News>) : super() {
        this.context = context
        this.itemList = itemlist
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nametype = this.itemList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = inflator.inflate(R.layout.row_grid, null)
        itemView.item_image.setImageResource(nametype.image_drawable!!)
        itemView.item_text.text = nametype.name!!
        itemView.cardView.setCardBackgroundColor(getRandomColorCode());
        itemView.layoutView.setBackgroundColor(getRandomColorCode());
        return itemView
    }

    fun getRandomColorCode(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    }
}