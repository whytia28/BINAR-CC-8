package com.example.binarchapter8.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarchapter8.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterPresenter.Listener {

    @Inject
    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.listener = this

        btn_register.setOnClickListener {
            presenter.registerUser(
                et_email.text.toString(),
                et_password.text.toString(),
                et_username.text.toString()
            )
        }

        btn_reset.setOnClickListener {
            presenter.resetEditText()
        }

        btn_back.setOnClickListener {
            presenter.onBackPress()
        }
    }

    override fun resetEditText() {
        et_email.setText("")
        et_username.setText("")
        et_password.setText("")
    }

    override fun onBackPress() {
        onBackPressed()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterSuccess() {
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onUsernameExist(existMessage: String) {
        Toast.makeText(this, existMessage, Toast.LENGTH_SHORT).show()
    }
}