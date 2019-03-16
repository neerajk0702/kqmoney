package com.kredivation.allquestionanswer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import com.kredivation.kqmoney.HomeActivity
import com.kredivation.kqmoney.R
import com.kredivation.kqmoney.activity.SignUpAcivity
import com.kredivation.kqmoney.framework.IAsyncWorkCompletedCallback
import com.kredivation.kqmoney.framework.ServiceCaller
import com.kredivation.kqmoney.model.ContentData
import com.kredivation.kqmoney.utility.Contants
import com.kredivation.kqmoney.utility.Utility
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

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
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }

        SignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpAcivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }


    fun attemptLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }



}
