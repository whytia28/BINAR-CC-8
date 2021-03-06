package com.example.binarchapter8.login


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.binarchapter8.R
import com.example.binarchapter8.main.MenuActivity
import com.example.binarchapter8.pojo.LoginResponse
import com.example.binarchapter8.register.RegisterActivity
import com.example.binarchapter8.sharedpref.MySharedPreferences
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {

    private lateinit var token: String

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)

        presenter.listener = this

        token = MySharedPreferences(applicationContext).getData("token").toString()

        btn_login.setOnClickListener {
            presenter.validateLogin(et_email.text.toString(), et_password.text.toString())
        }

        btn_reset.setOnClickListener {
            presenter.resetEditText()
        }

        tv_disini.setOnClickListener {
            presenter.goToRegisterActivity()
        }
    }

    override fun resetEditText() {
        et_email.setText("")
        et_password.setText("")
    }

    override fun onLoginSuccess() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onLoginFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onFieldEmpty(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goToMenuActivity(data: LoginResponse.Data) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
        finish()
    }

    override fun saveToken(key: String, data: String) {
        MySharedPreferences(applicationContext).putData(key, data)
    }

    override fun goToRegisterActivity() {
        val goToRegisterActivity = Intent(this, RegisterActivity::class.java)
        startActivity(goToRegisterActivity)
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progress_bar.visibility = View.GONE
    }


}