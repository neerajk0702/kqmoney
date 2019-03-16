package com.kredivation.allquestionanswer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kredivation.kqmoney.ViewQAModelActivity
import com.kredivation.kqmoney.adapter.CatTypeRecyclerAdapter
import com.kredivation.kqmoney.adapter.CustomGridViewAdapter
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject
import java.util.ArrayList
import android.view.View
import android.widget.Toast
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.framework.IAsyncWorkCompletedCallback
import com.kredivation.kqmoney.framework.ServiceCaller
import com.kredivation.kqmoney.model.Result
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import org.json.JSONException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG: String = "MainActivity"
    private lateinit var linearLayoutManager: LinearLayoutManager
    var adapter: CustomGridViewAdapter? = null
    var NewssList = ArrayList<News>()
    var typeList = ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

   /*     linearLayoutManager = LinearLayoutManager(this)
        val jsonString: String = readJsonFromKotlinFile()
        val newsList: ArrayList<News> = parseJsonStringToNewsList(jsonString)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2);
       // recyclerView.adapter = CatTypeRecyclerAdapter(newsList);

        recyclerView.setAdapter(CatTypeRecyclerAdapter(newsList, object : CatTypeRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(item: News) {
                val intent = Intent(baseContext, ViewQAModelActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }))*/


       /* NewssList.add(News("Coffee", R.drawable.questionanswer))
        NewssList.add(News("Espersso", R.drawable.questionanswer))
        NewssList.add(News("French Fires", R.drawable.questionanswer))
        NewssList.add(News("Honey", R.drawable.questionanswer))
        NewssList.add(News("Strawberry", R.drawable.questionanswer))
        NewssList.add(News("Sugar cubes", R.drawable.questionanswer))
        NewssList.add(News("Strawberry", R.drawable.questionanswer))
        NewssList.add(News("Sugar cubes", R.drawable.questionanswer))
     */
        getAllQuestionCategory();
        gridView.adapter = adapter
        adapter = CustomGridViewAdapter(this, NewssList)
    }

/*
    private fun parseJsonStringToNewsList(jsonString: String): ArrayList<News> {
        val newsList: ArrayList<News> = ArrayList<News>(0)
        val newsArray = JSONArray(jsonString)
        var i = 0
        var numIterations = newsArray.length()
        while (i < numIterations) {
            val newsObject: JSONObject = newsArray.getJSONObject(i)
            val news = News()
            news.news_title = newsObject.getString("news_title")
            news.news_image_url = newsObject.getString("news_image_url")
            news.news_source = newsObject.getString("news_source")
            news.news_detail = newsObject.getString("news_detail")
            news.news_url = newsObject.getString("news_url")
            news.id = newsObject.getInt("id")
            newsList.add(news)
            i++
        }
        return newsList
    }

    private fun readJsonFromKotlinFile(): String {
        var inputString = ""
        try {
            val inputStream: InputStream = assets.open("news_data_file.json")
            inputString = inputStream.bufferedReader().use { it.readText() }
            Log.d(TAG, inputString)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return inputString
    }*/

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    //get getAllQuestionCategory
    private fun getAllQuestionCategory() {
        if (Utility.isOnline(this@MainActivity)) {
            val serviceURL = Contants.BASE_URL + Contants.AllQuestionCategory ;
            val `object` = JSONObject()
            val serviceCaller = ServiceCaller(this@MainActivity)
            serviceCaller.CallCommanGetServiceMethod(
                serviceURL,
                `object`,
                "getTeamList",
                object : IAsyncWorkCompletedCallback {
                    override fun onDone(workName: String, isComplete: Boolean) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        if (isComplete && workName != null) {
                            try {
                                val mainObj = JSONObject(workName)
                                val status = mainObj.optBoolean("status")
                                if (status) {
                                    val dataArray = mainObj.optJSONArray("result")
                                    if (dataArray != null) {
                                        for (i in 0 until dataArray.length()) {
                                            val newsObject: JSONObject = dataArray.getJSONObject(i)
                                            val typeData = News()
                                            typeData.name = newsObject.getString("name")
                                            NewssList.add(typeData)
                                        }
                                        adapter?.notifyDataSetChanged()
                                    }
                                }
                            } catch (e: JSONException) {
                                // e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(this@MainActivity, Contants.Error, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        } else {
            Toast.makeText(this@MainActivity, Contants.OFFLINE_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }
}
