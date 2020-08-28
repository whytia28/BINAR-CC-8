package com.example.binarchapter8.main.ui.profile

import android.graphics.Bitmap
import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.pojo.GetProfileResponse
import com.example.binarchapter8.pojo.UpdateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*


class ProfilePresenter(private val apiService: ApiService) {

    var listener: Listener? = null


    fun getProfileUser(token: String) {
        listener?.showProgressBar()
        apiService.getProfileUser(token).enqueue(object : Callback<GetProfileResponse> {
            override fun onResponse(
                call: Call<GetProfileResponse>,
                response: Response<GetProfileResponse>
            ) {
                response.body()?.let {
                    listener?.showProfile(it.data.username, it.data.email, it.data.photo)
                }
                listener?.hiddenProgressBar()
            }

            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                t.message?.let {
                    listener?.showProfileFailed(it)
                }
            }

        })
    }

    fun updateUser(token: String, bitmap: Bitmap, username: String, email: String) {
        listener?.showProgressBar()
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("username", username)
        builder.addFormDataPart("email", email)

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, bos)

        builder.addFormDataPart(
            "photo", "photo.jpg",
            RequestBody.create(MultipartBody.FORM, bos.toByteArray())
        )

        val map = HashMap<String, String>()
        map["username"] = username
        map["email"] = email
        apiService.updateUser(token, builder.build()).enqueue(object : Callback<UpdateResponse> {
            override fun onResponse(
                call: Call<UpdateResponse>,
                response: Response<UpdateResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        listener?.showProfile(it.username, it.email, it.photo)
                        listener?.showSetupUi()
                    }
                    listener?.hiddenProgressBar()
                } else {
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        listener?.onUpdateFailed(jsonObject.getString("errors"))
                    }

                }
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                t.message?.let {
                    listener?.onUpdateFailed(it)
                }
            }

        })
    }

    fun editProfile() {
        listener?.showEditUi()
    }
    fun showSetupUi() {
        listener?.showSetupUi()
    }

    interface Listener {
        fun onUpdateSuccess()
        fun onUpdateFailed(errorMessage: String)
        fun showProfile(username: String, email: String, photo: String)
        fun showProfileFailed(errorMessage: String)
        fun showEditUi()
        fun showSetupUi()
        fun showProgressBar()
        fun hiddenProgressBar()
    }
}