package com.example.binarchapter7.network

import com.example.binarchapter7.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST ("v1/auth/login")
    fun validateLogin(@Body body: PostLoginBody): Call<LoginResponse>

    @POST("v1/auth/register")
    fun registerUser(@Body bodyRegister: PostBodyRegister): Call<RegisterResponse>

//    @Multipart
//    @PUT("v1/users")
////    fun updateUser() : Call<PutUpdateResponse> uploadImage(@Part)
}