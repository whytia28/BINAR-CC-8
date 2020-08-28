package com.example.binarchapter8.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SharedPreferencesModule(context: Context) {
    private val context: Context

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("userData", Context.MODE_PRIVATE)
    }

    init {
        this.context = context
    }
}