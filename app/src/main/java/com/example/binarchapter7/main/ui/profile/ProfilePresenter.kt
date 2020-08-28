package com.example.binarchapter7.main.ui.profile

import android.content.SharedPreferences
import com.example.binarchapter7.network.ApiService
import com.example.binarchapter7.pojo.LoginResponse
import com.example.binarchapter7.pojo.PutUpdateBody
import com.example.binarchapter7.pojo.PutUpdateResponse
import com.example.binarchapter7.pojo.UpdateResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfilePresenter(private val apiService: ApiService) {

    var listener: Listener? = null


    fun updateUser(result: LoginResponse.Data) {
//        val token = result.token
//        val objectPut = PutUpdateBody(result.email, result.username)
//        val requestBody = file.asRequestBody("image*".toMediaTypeOrNull())
//        val photo = MultipartBody.Part.create(MediaType.parse("image/*"))
//        apiService.updateUser(token, result.id).enqueue(object : Callback<UpdateResponse> {
//            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
//                t.message?.let {
//                    listener?.onUpdateFailed(it)
//                }
//            }
//
//            override fun onResponse(
//                call: Call<PutUpdateResponse>,
//                response: Response<UpdateResponse>
//            ) {
//                if (response.code() == 200) {
//                    response.body()?.data.let {
//                        listener?.showProfile()
//                    }
//                }else {
//                    response.errorBody()?.string()?.let {
//                        val jsonObject = JSONObject(it)
//                        listener?.onUpdateFailed(jsonObject.getString("errors"))
//                    }
//                }
//            }
//
//        })
    }

    fun showProfile() {
        listener?.showProfile()
    }

    fun editProfile() {
        listener?.showEditUi()
    }

    interface Listener {
        fun onUpdateSuccess()
        fun onUpdateFailed(errorMessage: String)
        fun showProfile()
        fun showEditUi()
    }
}