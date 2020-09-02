package com.example.binarchapter8.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.binarchapter8.R
import com.example.binarchapter8.login.LoginActivity
import com.example.binarchapter8.sharedpref.MySharedPreferences
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject

class MenuActivity : AppCompatActivity(), MenuActivityPresenter.Listener {

    @Inject
    lateinit var menuActivityPresenter: MenuActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(menu_actionbar)
        menuActivityPresenter.listener = this


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
            MySharedPreferences(this).deleteData()
            startActivity(loginIntent)
            menuActivityPresenter.onLogoutSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLogoutSuccess() {
        Toast.makeText(this, getString(R.string.logout_success), Toast.LENGTH_SHORT).show()
        finish()
    }

}