package com.example.binarchapter8.sharedpref

import android.content.Context


class MySharedPreferences(val context: Context) {

    private var mSharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    fun putData(key: String, data: String) {
        mSharedPreferences.edit().putString(key, data).apply()
    }

    fun getData(key: String): String? {
        return mSharedPreferences.getString(key, "")
    }
}