package com.example.binarchapter7.login


import com.example.binarchapter7.network.ApiService
import com.example.binarchapter7.pojo.LoginResponse
import com.example.binarchapter7.pojo.PostLoginBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun validateLogin(email: String, password: String) {

        val user = PostLoginBody(email, password)

        apiService.validateLogin(user).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                listener?.onLoginFailed(t.toString())
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        listener?.goToMenuActivity(it)
                    }
                } else if (email.isEmpty() && password.isEmpty()) {
                    listener?.onFieldEmpty(response.message())
                } else {
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        listener?.onLoginFailed(jsonObject.getString("errors"))
                    }
                }
            }
        })
    }

    fun resetEditText() {
        listener?.resetEditText()
    }

    fun goToRegisterActivity() {
        listener?.goToRegisterActivity()
    }

    interface Listener {
        fun resetEditText()
        fun onLoginSuccess(message: String)
        fun onLoginFailed(errorMessage: String)
        fun onFieldEmpty(message: String)
        fun goToMenuActivity(data: LoginResponse.Data)
        fun goToRegisterActivity()
    }
}