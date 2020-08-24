package com.example.binarchapter7.pojo


import com.google.gson.annotations.SerializedName

data class PutUpdateBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String
)