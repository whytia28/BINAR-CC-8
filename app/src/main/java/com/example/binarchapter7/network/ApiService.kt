package com.example.binarchapter7.network

import com.example.binarchapter7.pojo.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST ("v1/auth/login")
    fun validateLogin(@Body body: PostLoginBody): Call<LoginResponse>

    @POST("v1/auth/register")
    fun registerUser(@Body bodyRegister: PostBodyRegister): Call<RegisterResponse>

    @Multipart
    @PUT("v1/users")
    fun updateUser(
        @Header("Authorization") auth: String,
        @Part photo: MultipartBody.Part, @PartMap map: Map<String, RequestBody>) : Call<UpdateResponse>

    @GET("v1/battle")
    fun getHistoryBattle(@Header ("Authorization") auth: String): Call <GetBattleResponse>
}