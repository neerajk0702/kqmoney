package com.kredivation.kqmoney.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kredivation.kqmoney.model.News
import com.squareup.picasso.Picasso
import android.widget.TextView
import com.kredivation.kqmoney.R
import kotlinx.android.synthetic.main.item_grid.view.*


class CatTypeRecyclerAdapter(private val items: List<News>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CatTypeRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView
        private val image: ImageView

        init {
            name = itemView.findViewById(R.id.tv_heading) as TextView
            image = itemView.findViewById(R.id.iv_cover) as ImageView
        }

        fun bind(item: News, listener: OnItemClickListener) {

            name.setText(item.news_title)
            Picasso.with(itemView.context).load(item.news_image_url).into(image)
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}