package com.kredivation.allquestionanswer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kredivation.kqmoney.R

class SplashScreenActivity : AppCompatActivity() {

    val isLogin = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        openLoginHomeActivity();
    }

    private fun openLoginHomeActivity() {
        Handler().postDelayed({
            if (isLogin) {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

        }, 3000)
    }
}
