package com.example.binarchapter7.main.ui.profile

import com.example.binarchapter7.network.ApiClient
import com.example.binarchapter7.pojo.PostLoginResponse
import com.example.binarchapter7.pojo.PutUpdateBody
import com.example.binarchapter7.pojo.PutUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(val listener: Listener) {

    fun updateUser (result: PostLoginResponse.Data) {
        val objectPut = PutUpdateBody(result.email, result.username)
        ApiClient.instance.updateUser(objectPut, result.id.toString()).enqueue(object : Callback<PutUpdateResponse> {
            override fun onFailure(call: Call<PutUpdateResponse>, t: Throwable) {
                t.message?.let {
                    listener.onUpdateFailed(it)
                }
            }

            override fun onResponse(
                call: Call<PutUpdateResponse>,
                response: Response<PutUpdateResponse>
            ) {
                response.body()?.result.let {
                    if (it != null) {
                        listener.onUpdateSuccess(it)
                    }
                }
            }

        })
    }

    fun showProfile() {
        listener.showProfile()
    }

    interface Listener {
        fun onUpdateSuccess(message: String)
        fun onUpdateFailed(errorMessage: String)
        fun showProfile()
    }
}