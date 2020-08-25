package com.example.binarchapter7.di

import android.content.Context
import com.example.binarchapter7.areaMain.PemainVsCpuPresenter
import com.example.binarchapter7.areaMain.PemainVsPemainPresenter
import com.example.binarchapter7.login.LoginPresenter
import com.example.binarchapter7.main.ui.profile.ProfilePresenter
import com.example.binarchapter7.network.ApiService
import com.example.binarchapter7.register.RegisterPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Singleton
    @Provides
    fun provideLoginPresenter(apiService: ApiService) : LoginPresenter {
        return LoginPresenter(apiService)
    }

    @Singleton
    @Provides
    fun provideRegisterPresenter(apiService: ApiService) : RegisterPresenter {
        return RegisterPresenter(apiService)
    }

    @Singleton
    @Provides
    fun providePemainVsPemainPresenter(context: Context, apiService: ApiService) : PemainVsPemainPresenter {
        return PemainVsPemainPresenter(context, apiService)
    }

    @Singleton
    @Provides
    fun providePemainVsCpu(context: Context, apiService: ApiService) : PemainVsCpuPresenter {
        return PemainVsCpuPresenter(context, apiService)
    }

    @Singleton
    @Provides
    fun profilePresenter(apiService: ApiService): ProfilePresenter {
        return ProfilePresenter(apiService)
    }
}