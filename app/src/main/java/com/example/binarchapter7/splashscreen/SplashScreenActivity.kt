package com.example.binarchapter7.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.binarchapter7.R
import com.example.binarchapter7.welcome.WelcomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    WelcomeActivity::class.java
                )
            )
            finish()
        }, 3000)
    }
}