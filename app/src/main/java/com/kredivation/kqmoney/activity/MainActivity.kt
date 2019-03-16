package com.kredivation.allquestionanswer

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.kredivation.kqmoney.adapter.CustomGridViewAdapter
import com.kredivation.kqmoney.model.News
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject
import java.util.ArrayList
import android.widget.Toast
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.framework.IServiceSuccessCallback
import com.kredivation.kqmoney.framework.ServiceHelper
import com.kredivation.kqmoney.utility.ASTProgressBar
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import org.json.JSONException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG: String = "MainActivity"
    private lateinit var linearLayoutManager: LinearLayoutManager
    var adapter: CustomGridViewAdapter? = null
    var NewssList = ArrayList<News>()

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

        getAllQuestionCategory();
        adapter = CustomGridViewAdapter(baseContext, NewssList)
        gridView.adapter = adapter
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

    var dotDialog: ASTProgressBar? = null
    //get getAllQuestionCategory
    private fun getAllQuestionCategory() {
        if (Utility.isOnline(this@MainActivity)) {
            dotDialog = ASTProgressBar(this@MainActivity)
            dotDialog!!.show()
            val serviceURL = Contants.BASE_URL + Contants.AllQuestionCategory;
            val jsonObj = JSONObject()
            ServiceHelper().callGetService(serviceURL, jsonObj, object : IServiceSuccessCallback {
                override fun onDone(doneWhatCode: String, result: String, error: String?) {
                    if (result != null) {
                        try {
                            val mainObj = JSONObject(result)
                            val status = mainObj.optBoolean("status")
                            if (status) {
                                val dataArray = mainObj.optJSONArray("result")
                                if (dataArray != null) {
                                    for (i in 0 until dataArray.length()) {
                                        val newsObject: JSONObject = dataArray.getJSONObject(i)
                                        val typeData = News()
                                        typeData.name = newsObject.getString("name")
                                        typeData.id = newsObject.getInt("id")
                                        NewssList.add(typeData)
                                    }
                                    adapter?.notifyDataSetChanged()
                                    //  adapter = CustomGridViewAdapter(baseContext, NewssList)
                                    //  gridView.adapter = adapter
                                }
                            }
                        } catch (e: JSONException) {
                            // e.printStackTrace();
                        }
                        if (dotDialog!!.isShowing()) {
                            dotDialog!!.dismiss()
                        }
                        //Log.d(Contants.LOG_TAG, "$methodNmae********$result")
                    } else {
                    }
                }
            })
        } else {
            Toast.makeText(this@MainActivity, Contants.OFFLINE_MESSAGE, Toast.LENGTH_SHORT).show()
        }


    }
}
