package com.kredivation.kqmoney.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.fragment_qamodel.view.*
import kotlinx.android.synthetic.main.fragment_qamodel_list.*

class MyQAModelRecyclerViewAdapter(
    private val mValues: List<News>
) : RecyclerView.Adapter<MyQAModelRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as News
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_qamodel, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mainQuestion.text = item.name
        holder.firstAnswer.setBackgroundResource(R.drawable.buy_button)


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)


        }


    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mainQuestion: TextView = mView.mainQuestion
        val firstAnswer: LinearLayout = mView.firstAnswer
        override fun toString(): String {
            return super.toString() + " '";
        }
    }
}
