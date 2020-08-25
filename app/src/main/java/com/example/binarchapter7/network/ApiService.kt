package com.example.binarchapter7.network

import com.example.binarchapter7.pojo.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST ("auth/login")
    fun validateLogin(@Body body: PostLoginBody): Call<LoginResponse>

    @POST("auth/register")
    fun registerUser(@Body bodyRegister: PostBodyRegister): Call<RegisterResponse>

    @PUT("{id}")
    fun updateUser(@Body updateBody: PutUpdateBody, @Path("id") id: String): Call<PutUpdateResponse>
}