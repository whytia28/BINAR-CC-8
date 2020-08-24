package com.example.binarchapter7.register

import com.example.binarchapter7.network.ApiClient
import com.example.binarchapter7.pojo.PostBodyRegister
import com.example.binarchapter7.pojo.PostRegisterResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(private val listener: Listener) {

    fun registerUser(email: String, password: String, username: String) {

        val userRegister = PostBodyRegister(email, password, username)

        ApiClient.instance.registerUser(userRegister).enqueue(object : Callback<PostRegisterResponse> {
            override fun onFailure(call: Call<PostRegisterResponse>, t: Throwable) {
                listener.onRegisterFailed(t.message.toString())
            }

            override fun onResponse(
                call: Call<PostRegisterResponse>,
                response: Response<PostRegisterResponse>
            ) {
                if (response.code() == 422) {
                    response.errorBody()?.string()?.let {
                        val jsonObjectFailed = JSONObject(it)
                        listener.onUsernameExist(jsonObjectFailed.getString("message"))
                    }

                } else {
                    response.body()?.message?.let { listener.onRegisterSuccess(it) }
                }
            }

        })
    }

    fun resetEditText() {
        listener.resetEditText()
    }

    fun onBackPress() {
        listener.onBackPress()
    }

    interface Listener {
        fun resetEditText()
        fun onBackPress()
        fun onRegisterFailed(message: String)
        fun onRegisterSuccess(successMessage: String)
        fun onUsernameExist(existMessage: String)
    }
}