package com.example.binarchapter7.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PostLoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    @Parcelize
    data class Data(
        @SerializedName("email")
        var email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("username")
        var username: String
    ) : Parcelable
}