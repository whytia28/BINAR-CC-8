package com.example.binarchapter7.login


import com.example.binarchapter7.network.ApiClient
import com.example.binarchapter7.pojo.PostLoginBody
import com.example.binarchapter7.pojo.PostLoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val listener: Listener) {

    fun validateLogin(email: String, password: String) {

        val user = PostLoginBody(email, password)

        ApiClient.instance.validateLogin(user).enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                listener.onLoginFailed(t.toString())
            }

            override fun onResponse(
                call: Call<PostLoginResponse>,
                response: Response<PostLoginResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let { listener.goToMenuActivity(it) }
                } else if (email.isEmpty() && password.isEmpty()) {
                    listener.onFieldEmpty(response.message())
                } else{
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        listener.onLoginFailed(jsonObject.getString("message"))
                    }
                }
            }

        })
    }

    fun resetEditText() {
        listener.resetEditText()
    }

    fun goToRegisterActivity() {
        listener.goToRegisterActivity()
    }

    interface Listener {
        fun resetEditText()
        fun onLoginSuccess(message: String)
        fun onLoginFailed(errorMessage: String)
        fun onFieldEmpty(message: String)
        fun goToMenuActivity(data: PostLoginResponse.Data)
        fun goToRegisterActivity()
    }
}