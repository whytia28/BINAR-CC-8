package com.example.binarchapter8.network

import com.example.binarchapter8.pojo.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST ("v1/auth/login")
    fun validateLogin(@Body body: PostLoginBody): Call<LoginResponse>

    @POST("v1/auth/register")
    fun registerUser(@Body bodyRegister: PostBodyRegister): Call<RegisterResponse>


    @PUT("v1/users")
    fun updateUser(
        @Header("Authorization") auth: String,
        @Body photo: RequestBody) : Call<UpdateResponse>

    @GET("v1/users")
    fun getProfileUser(@Header("Authorization") auth: String): Call<GetProfileResponse>

    @GET("v1/battle")
    fun getHistoryBattle(@Header ("Authorization") auth: String): Call <GetBattleResponse>

    @POST("v1/battle")
    fun saveHistoryBattle(@Header ("Authorization") auth: String, @Body bodyHistory: PostBattleBody) : Call<PostBattleResponse>

}