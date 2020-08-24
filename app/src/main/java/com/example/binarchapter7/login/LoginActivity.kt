package com.example.binarchapter7.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarchapter7.R
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.pojo.PostLoginResponse
import com.example.binarchapter7.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

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

    override fun goToMenuActivity(data: PostLoginResponse.Data) {
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