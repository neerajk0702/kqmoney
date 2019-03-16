package com.kredivation.kqmoney.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.kredivation.allquestionanswer.LoginActivity
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.framework.FileUploaderHelper
import com.kredivation.kqmoney.framework.FileUploaderHelperWithProgress
import com.kredivation.kqmoney.model.ContentData
import com.kredivation.kqmoney.utility.ASTProgressBar
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up_acivity.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.HashMap

class SignUpAcivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_acivity)

        signBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        signUp.setOnClickListener { callSignup() }
    }


    var dotDialog: ASTProgressBar? = null
    private fun callSignup() {
        val payloadList = HashMap<String, String>()
        if (Utility.isOnline(this)) {
            dotDialog = ASTProgressBar(this@SignUpAcivity)
            dotDialog!!.show()
            val url = Contants.BASE_URL + Contants.Registration
            payloadList["name"] = name.text.toString();
            payloadList["email"] = emailid.text.toString();
            payloadList["phone"] = phoneno.text.toString();
            payloadList["address"] = address.text.toString();
            val multipartBody = setMultipartBodyVaule()
            val fileUploaderHelper =
                object : FileUploaderHelper(this@SignUpAcivity, payloadList, multipartBody, url) {
                    override fun receiveData(result: String?) {
                        val data = Gson().fromJson<ContentData>(result, ContentData::class.java!!)
                        if (data != null && data.getStatus().equals(true)) {
                            Toast.makeText(this@SignUpAcivity, "Signup Successful", Toast.LENGTH_LONG)
                                .show()
                            val intentLoggedIn = Intent(this@SignUpAcivity, LoginActivity::class.java)
                            startActivity(intentLoggedIn)
                        } else {
                            Toast.makeText(this@SignUpAcivity, "Signup Not Successful", Toast.LENGTH_LONG)
                                .show()
                        }

                    }
                }
            fileUploaderHelper.execute()
        } else {
            showToast(Contants.OFFLINE_MESSAGE)
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


    private fun showToast(message: String) {
        Toast.makeText(this@SignUpAcivity, message, Toast.LENGTH_LONG).show()
    }
}
