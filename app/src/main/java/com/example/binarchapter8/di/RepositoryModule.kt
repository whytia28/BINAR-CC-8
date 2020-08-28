package com.example.binarchapter8.di

import com.example.binarchapter8.BuildConfig
import com.example.binarchapter8.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RepositoryModule {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        val client = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}