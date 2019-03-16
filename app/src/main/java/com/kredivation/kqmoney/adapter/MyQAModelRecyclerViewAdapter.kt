package com.kredivation.kqmoney.adapter

import android.annotation.SuppressLint
import android.content.Context
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
import kotlinx.android.synthetic.main.question_view_item.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MyQAModelRecyclerViewAdapter(
    private val mValues: List<News>,
    var context: Context? = null
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
        holder.mainQuestion.text = item.getTitle()
        holder.questiondesc.text = item.getDescription()

        //  holder.firstAnswer.setBackgroundResource(R.drawable.buy_button)
        val mainObj = JSONObject(item.getAns_json())
        val Ans_jsonArray = mainObj.optJSONArray("date")

        if (Ans_jsonArray != null) {
            for (i in 0 until Ans_jsonArray.length()) {
                try {
                    val `object` = Ans_jsonArray.getJSONObject(i)
                    val ans = `object`.optString("ans")
                    val id = `object`.optString("id")
                    addQAnserView(ans, id, holder.questionLayoutView)
                } catch (e: JSONException) {
                    //e.printStackTrace();
                }
            }
            with(holder.mView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }


    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mainQuestion: TextView = mView.mainQuestion
        val questiondesc: TextView = mView.questiondesc
        val questionLayoutView: LinearLayout = mView.questionLayoutView

        override fun toString(): String {
            return super.toString() + " '";
        }
    }

    //add free service layout in runtime
    fun addQAnserView(name: String, option: String, questionLayoutView: LinearLayout) {
        val inflater = LayoutInflater.from(context)
        val inflatedLayout = inflater.inflate(R.layout.question_view_item, null)
        val optionTxt = inflatedLayout.optionTxt
        val answerTextt = inflatedLayout.answerTextt
        optionTxt.setText(option)
        answerTextt.setText(name)
        questionLayoutView.addView(inflatedLayout)

    }
}
