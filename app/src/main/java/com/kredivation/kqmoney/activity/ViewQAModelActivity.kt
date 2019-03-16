package com.kredivation.kqmoney

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.kredivation.kqmoney.adapter.MyQAModelRecyclerViewAdapter
import com.kredivation.kqmoney.framework.IServiceSuccessCallback
import com.kredivation.kqmoney.framework.ServiceHelper
import com.kredivation.kqmoney.model.News
import com.kredivation.kqmoney.utility.ASTProgressBar
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import kotlinx.android.synthetic.main.fragment_qamodel_list.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class ViewQAModelActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    val newsList: ArrayList<News> = ArrayList<News>(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_qamodel_list)
        setSupportActionBar(toolbar)
        linearLayoutManager = LinearLayoutManager(this)
        list.layoutManager = LinearLayoutManager(this)
        list.setHasFixedSize(true)
        // recyclerView.layoutManager = GridLayoutManager(this, 2);
        getAllQuestion()
        list.adapter = MyQAModelRecyclerViewAdapter(newsList, this)
    }

    var dotDialog: ASTProgressBar? = null
    //get getAllQuestionCategory
    private fun getAllQuestion() {
        if (Utility.isOnline(this@ViewQAModelActivity)) {
            dotDialog = ASTProgressBar(this@ViewQAModelActivity)
            dotDialog!!.show()
            val serviceURL = Contants.BASE_URL + Contants.AllQuestion;
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
                                        typeData.id = newsObject.getInt("id")
                                        typeData.setTitle(newsObject.getString("title"))
                                        typeData.setDescription(newsObject.getString("description"))
                                        typeData.setAns_json(newsObject.getString("ans_json"))
                                        typeData.setTotal_coins(newsObject.getString("total_coins"))
                                        typeData.setWrong_coins(newsObject.getString("wrong_coins"))
                                        typeData.setAttempted(newsObject.getString("attempted"))
                                        typeData.setCorrect_coins(newsObject.getString("correct_coins"))
                                        typeData.setStatus(newsObject.getString("status"))
                                        typeData.setCreatedatetime(newsObject.getString("createdatetime"))
                                        typeData.setComment(newsObject.getString("comment"))
                                        typeData.setVerifyUserId(newsObject.getString("verifyUserId"))
                                        typeData.setCategoryId(newsObject.getString("categoryId"))
                                        newsList.add(typeData)
                                    }
                                    list.adapter?.notifyDataSetChanged()
                                    list.adapter = MyQAModelRecyclerViewAdapter(newsList, baseContext)
                                    //list.adapter = MyQAModelRecyclerViewAdapter(newsList)
                                    // adapter = CustomGridViewAdapter(baseContext, NewssList)
                                    // gridView.adapter = adapter
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
            Toast.makeText(this@ViewQAModelActivity, Contants.OFFLINE_MESSAGE, Toast.LENGTH_SHORT).show()
        }


    }

}
