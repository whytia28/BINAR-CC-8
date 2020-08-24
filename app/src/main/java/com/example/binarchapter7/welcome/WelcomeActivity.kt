package com.example.binarchapter7.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.binarchapter7.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val mFragmentManager = supportFragmentManager
        val fragmentTransaction = mFragmentManager.beginTransaction()
        val mPemainFragment = PemainFragment()

        fragmentTransaction.add(
            R.id.frame_container,
            mPemainFragment,
            PemainFragment::class.java.simpleName
        )
        fragmentTransaction.commit()
    }
}