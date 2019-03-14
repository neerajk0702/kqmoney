package com.kredivation.allquestionanswer

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
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.adapter.CatTypeRecyclerAdapter
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG: String = "MainActivity"
    private lateinit var linearLayoutManager: LinearLayoutManager

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
        linearLayoutManager = LinearLayoutManager(this)
        val jsonString: String = readJsonFromKotlinFile()
        val newsList: ArrayList<News> = parseJsonStringToNewsList(jsonString)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
      //  recyclerView.layoutManager = GridLayoutManager(this, 2);
        recyclerView.adapter = CatTypeRecyclerAdapter(newsList)


    }

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
    }

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
}
