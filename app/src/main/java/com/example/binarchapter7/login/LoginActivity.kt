package com.example.binarchapter7.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarchapter7.R
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.pojo.LoginResponse
import com.example.binarchapter7.register.RegisterActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)

        presenter.listener = this

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

    override fun onLoginSuccess(message: String) {
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

    override fun goToRegisterActivity() {
        val goToRegisterActivity = Intent(this, RegisterActivity::class.java)
        startActivity(goToRegisterActivity)
    }

}