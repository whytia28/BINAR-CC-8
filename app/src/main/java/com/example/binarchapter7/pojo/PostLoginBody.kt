package com.example.binarchapter7.pojo


import com.google.gson.annotations.SerializedName

data class PostLoginBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)