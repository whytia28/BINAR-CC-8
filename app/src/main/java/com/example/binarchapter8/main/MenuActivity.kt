package com.example.binarchapter8.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.binarchapter8.R
import com.example.binarchapter8.login.LoginActivity
import com.example.binarchapter8.pojo.LoginResponse
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var result: LoginResponse.Data
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(menu_actionbar)

        intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
            result = it
        }
        username = result.username
        email = result.email

        sharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_battle, R.id.navigation_history, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val changeIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(changeIntent)
        } else if (item.itemId == R.id.logout) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            startActivity(loginIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}