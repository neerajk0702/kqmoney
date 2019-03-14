package com.kredivation.kqmoney.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid.view.*


class CatTypeRecyclerAdapter(private val news: ArrayList<News>) :
    RecyclerView.Adapter<CatTypeRecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: CatTypeRecyclerAdapter.ViewHolder, position: Int) {
        val itemNews = news[position]
        holder.bindNews(itemNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatTypeRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return ViewHolder(v)

    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var news: News? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")



//            val context = itemView.context
//            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
//            showPhotoIntent.putExtra(PHOTO_KEY, photo)
//            context.startActivity(showPhotoIntent)
        }

        fun bindNews(news: News) {
            this.news = news
            Picasso.with(view.context).load(news.news_image_url).into(view.iv_cover)
            view.tv_heading.text = news.news_title
            view.tv_source.text = news.news_source
        }
    }
}