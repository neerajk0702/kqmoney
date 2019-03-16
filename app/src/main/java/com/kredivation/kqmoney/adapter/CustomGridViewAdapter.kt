package com.kredivation.kqmoney.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kredivation.allquestionanswer.MainActivity
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.ViewQAModelActivity
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

        itemView.item_text.text = nametype.getNames()!!
        itemView.cardView.setCardBackgroundColor(getRandomColorCode());
        itemView.layoutView.setBackgroundColor(getRandomColorCode());
        if(position==0){
            itemView.item_image.setImageResource(R.drawable.tech)
            itemView.setOnClickListener {  openViewQuestionActivity()}
        }else if(position==1){
            itemView.item_image.setImageResource(R.drawable.social)

            itemView.setOnClickListener {  openViewQuestionActivity()}
        }else if(position==2){
            itemView.item_image.setImageResource(R.drawable.medical)
        }else if(position==3){
            itemView.item_image.setImageResource(R.drawable.sports)
        }
        else if(position==4){
            itemView.item_image.setImageResource(R.drawable.science)
        }
        else if(position==5){
            itemView.item_image.setImageResource(R.drawable.reli)
        }
        else if(position==6){
            itemView.item_image.setImageResource(R.drawable.his)
        }
        else if(position==7){
            itemView.item_image.setImageResource(R.drawable.sports)
        }
        else if(position==8){
            itemView.item_image.setImageResource(R.drawable.other)
        }



        return itemView
    }

    fun getRandomColorCode(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    }

    fun openViewQuestionActivity(){
        val intent = Intent(context, ViewQAModelActivity::class.java)
        context?.startActivity(intent)
    }
}