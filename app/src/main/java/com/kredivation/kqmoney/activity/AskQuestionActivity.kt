package com.kredivation.kqmoney.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.kredivation.allquestionanswer.LoginActivity
import com.kredivation.allquestionanswer.MainActivity
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.framework.FileUploaderHelper
import com.kredivation.kqmoney.model.ContentData
import com.kredivation.kqmoney.utility.ASTProgressBar
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import com.kredivation.kqmoney.utility.Utility.showToast
import kotlinx.android.synthetic.main.activity_ask_question.*
import kotlinx.android.synthetic.main.activity_sign_up_acivity.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import org.json.JSONObject
import java.util.HashMap
import org.json.JSONArray


class AskQuestionActivity : AppCompatActivity() {
    var userid: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_question)
        getUserInfo();
        postQuestion.setOnClickListener { addQuestion() }
    }


    fun getUserInfo() {
        val sharedPreference = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
        userid = sharedPreference.getString("id", "1")
    }

    var dotDialog: ASTProgressBar? = null
    private fun addQuestion() {
        val payloadList = HashMap<String, String>()
        if (Utility.isOnline(this)) {
            dotDialog = ASTProgressBar(this@AskQuestionActivity)
            dotDialog!!.show()
            val url = Contants.BASE_URL + Contants.addQuestion
            payloadList["title"] = questionTitle.text.toString()
            payloadList["description"] = questionDec.text.toString()
            val mainObj = JSONObject()
            val arr = JSONArray()
            arr.put(edittextAnswer1.text.toString())
            arr.put(edittextAnswer2.text.toString())
            arr.put(edittextAnswer3.text.toString())
            arr.put(edittextAnswer4.text.toString())
            mainObj.put("date", arr)
            payloadList["ans_json"] = mainObj.toString()
            payloadList["total_coins"] = totalcoin.text.toString()
            payloadList["wrong_coins"] = wrongCoin.text.toString()
            payloadList["attempted"] = "1"
            payloadList["userId"] = userid;
            payloadList["status"] = "12"
            payloadList["categoryId"] = questionCat.selectedItemPosition.toString()
            val multipartBody = setMultipartBodyVaule()
            val fileUploaderHelper =
                object : FileUploaderHelper(this@AskQuestionActivity, payloadList, multipartBody, url) {
                    override fun receiveData(result: String?) {
                        val data = Gson().fromJson<ContentData>(result, ContentData::class.java!!)
                        if (data != null && data.getStatus().equals(true)) {
                            Toast.makeText(this@AskQuestionActivity, "Question Post Successful", Toast.LENGTH_LONG)
                                .show()
                            val intentLoggedIn = Intent(this@AskQuestionActivity, MainActivity::class.java)
                            startActivity(intentLoggedIn)
                        } else {
                            Toast.makeText(this@AskQuestionActivity, "Question Not Post Successful", Toast.LENGTH_LONG)
                                .show()
                        }

                    }
                }
            fileUploaderHelper.execute()
        } else {
            showToast(this, Contants.OFFLINE_MESSAGE)
        }
        if (dotDialog!!.isShowing()) {
            dotDialog!!.dismiss()
        }
    }

    //add pm install images into MultipartBody for send as multipart
    private fun setMultipartBodyVaule(): MultipartBody.Builder {
        val MEDIA_TYPE = MediaType.parse("image/png")
        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        return multipartBody
    }

}
