package com.example.binarchapter8.login


import android.content.Context
import com.example.binarchapter8.sharedpref.MySharedPreferences
import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.pojo.LoginResponse
import com.example.binarchapter8.pojo.PostLoginBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter(val apiService: ApiService) {

    var listener: Listener? = null

    fun validateLogin(context: Context, email: String, password: String) {
        listener?.showProgressBar()
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
                        MySharedPreferences(context).putData("token", "Bearer ${it.token}")
                    }
                } else if (email.isEmpty() && password.isEmpty()) {
                    listener?.onFieldEmpty(response.message())
                } else {
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        listener?.onLoginFailed(jsonObject.getString("errors"))
                    }
                }
                listener?.hiddenProgressBar()
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
        fun showProgressBar()
        fun hiddenProgressBar()
    }
}