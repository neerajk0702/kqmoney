package com.kredivation.kqmoney

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kredivation.kqmoney.adapter.CustomGridViewAdapter
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.activity_home.*

import java.util.ArrayList

class HomeActivity : AppCompatActivity() {

    var adapter: CustomGridViewAdapter? = null
    var NewssList = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        NewssList.add(News("Coffee", R.drawable.questionanswer))
        NewssList.add(News("Espersso", R.drawable.questionanswer))
        NewssList.add(News("French Fires", R.drawable.questionanswer))
        NewssList.add(News("Honey", R.drawable.questionanswer))
        NewssList.add(News("Strawberry", R.drawable.questionanswer))
        NewssList.add(News("Sugar cubes", R.drawable.questionanswer))
        NewssList.add(News("Strawberry", R.drawable.questionanswer))
        NewssList.add(News("Sugar cubes", R.drawable.questionanswer))
        adapter = CustomGridViewAdapter(this, NewssList)

        gridView1.adapter = adapter
    }

}