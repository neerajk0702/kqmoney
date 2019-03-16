package com.kredivation.allquestionanswer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.activity.SignUpAcivity
import com.kredivation.kqmoney.framework.FileUploaderHelper
import com.kredivation.kqmoney.utility.ASTProgressBar
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.s

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                if (etUserName.text.toString() != null || !etUserName.text.toString().equals("")) {
                    attemptLogin(etUserName.text.toString())
                }

                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener {
            if (etUserName.text.toString() != null || !etUserName.text.toString().equals("")) {
                attemptLogin(etUserName.text.toString())
            }
        }

        SignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpAcivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }


    fun attemptLogin(phonen: String) {
        loginData(phonen);

    }

    var UserInfo: SharedPreferences? = null


    var dotDialog: ASTProgressBar? = null
    private fun loginData(phonenO: String) {
        if (Utility.isOnline(this@LoginActivity)) {
            dotDialog = ASTProgressBar(this@LoginActivity)
            dotDialog!!.show()
            val payloadList = HashMap<String, String>()
            val serviceURL = Contants.BASE_URL + Contants.UserDetails
            try {
                payloadList["phone"] = etUserName.text.toString()
                val multipartBody = setMultipartBodyVaule()
                val fileUploaderHelper =
                    object : FileUploaderHelper(this@LoginActivity, payloadList, multipartBody, serviceURL) {
                        override fun receiveData(result: String?) {
                            if (result != null) {
                                parseLoginServiceData(result)
                            } else {
                                Utility.showToast(this@LoginActivity, Contants.Error)
                                if (dotDialog!!.isShowing()) {
                                    dotDialog!!.dismiss()
                                }

                            }
                        }
                    }
                fileUploaderHelper.execute()
            } catch (e: JSONException) {
            }
        } else {
            Utility.showToast(this@LoginActivity, Contants.OFFLINE_MESSAGE)//off line msg....
        }
    }


    /*
 *
 * Parse and Validate Login Service Data
 */
    private fun parseLoginServiceData(result: String?) {
        if (result != null) {
            try {
                val sharedPreference = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
                val mainObj = JSONObject(result)
                val status = mainObj.optBoolean("status")
                if (status != null && status.equals(true)) {
                    val dataArray = mainObj.optJSONArray("result")
                    if (dataArray != null) {
                        for (i in 0 until dataArray.length()) {
                            val userObject: JSONObject = dataArray.getJSONObject(i)
                            val id: String = userObject.getString("id")
                            val name: String = userObject.getString("name")
                            val email: String = userObject.getString("email")
                            val phone: String = userObject.getString("phone")
                            val address: String = userObject.getString("address")
                            val professional: String = userObject.getString("professional")
                            val profile_image: String = userObject.getString("profile_image")
                            val total_coins: String = userObject.getString("total_coins")
                            var editor = sharedPreference.edit()
                            editor.putString("name", name)
                            editor.putString("email", email)
                            editor.putString("id", id)
                            editor.putString("phone", phone)
                            editor.putString("address", address)
                            editor.putString("professional", professional)
                            editor.putString("profile_image", profile_image)
                            editor.putString("total_coins", total_coins)
                            editor.commit()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(this, "Please Provide Correct Phone No", Toast.LENGTH_SHORT).show()
                }
                if (dotDialog!!.isShowing()) {
                    dotDialog!!.dismiss()
                }
            } catch (e: Exception) {
                // TODO Auto-generated catch block
                //
            }

        }

    }

    //add pm install images into MultipartBody for send as multipart
    private fun setMultipartBodyVaule(): MultipartBody.Builder {
        val MEDIA_TYPE = MediaType.parse("image/png")
        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        return multipartBody
    }
}

