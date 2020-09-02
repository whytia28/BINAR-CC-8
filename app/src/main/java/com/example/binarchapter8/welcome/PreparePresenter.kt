package com.example.binarchapter8.welcome

import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.pojo.AuthResponse
import com.example.binarchapter8.sharedpref.MySharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreparePresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun autoLogin(token: String) {
        listener?.showProgressBar()
        apiService.autoLogin(token).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        listener?.goToMenuActivity(it)
                    }
                }
                listener?.hiddenProgressBar()
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                t.message?.let {
                    listener?.onLoginFailed(it)
                    listener?.goToLoginActivity()
                }
            }

        })
    }

    fun goToLoginActivity() {
        listener?.goToLoginActivity()
    }

    interface Listener {

        fun onLoginFailed(errorMessage: String)
        fun goToMenuActivity(data: AuthResponse.Data)
        fun showProgressBar()
        fun hiddenProgressBar()
        fun goToLoginActivity()
    }
}