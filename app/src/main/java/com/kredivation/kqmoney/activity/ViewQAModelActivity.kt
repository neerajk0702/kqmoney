package com.kredivation.kqmoney

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kredivation.kqmoney.adapter.MyQAModelRecyclerViewAdapter
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.fragment_qamodel_list.*
import java.util.ArrayList

class ViewQAModelActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_qamodel_list)
        val newsList: ArrayList<News> = ArrayList<News>(0)

        linearLayoutManager = LinearLayoutManager(this)
        list.layoutManager = LinearLayoutManager(this)
        list.setHasFixedSize(true)
        // recyclerView.layoutManager = GridLayoutManager(this, 2);

        val news = News()
        for (i in 1..8) {
            news.name = "Q.Who is India First PM"
            news.qname = "A"
            news.image_drawable = R.drawable.questionanswer;
            newsList.add(news)
        }

        list.adapter = MyQAModelRecyclerViewAdapter(newsList)
    }


}
