package com.example.binarchapter7.register

import com.example.binarchapter7.network.ApiService
import com.example.binarchapter7.pojo.PostBodyRegister
import com.example.binarchapter7.pojo.RegisterResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun registerUser(email: String, password: String, username: String) {

        val userRegister = PostBodyRegister(email, password, username)

        apiService.registerUser(userRegister).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.code() == 422) {
                    response.errorBody()?.string()?.let {
                        val jsonObjectFailed = JSONObject(it)
                        listener?.onUsernameExist(jsonObjectFailed.getString("errors"))
                    }
                } else {
                    response.body()?.success.let {
                        if (it != null) {
                            listener?.onRegisterSuccess(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                listener?.onRegisterFailed(t.message.toString())
            }

        })

    }

    fun resetEditText() {
        listener?.resetEditText()
    }

    fun onBackPress() {
        listener?.onBackPress()
    }

    interface Listener {
        fun resetEditText()
        fun onBackPress()
        fun onRegisterFailed(message: String)
        fun onRegisterSuccess(successMessage: String)
        fun onUsernameExist(existMessage: String)
    }
}