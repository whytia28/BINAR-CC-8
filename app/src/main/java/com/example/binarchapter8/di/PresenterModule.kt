package com.example.binarchapter8.di


import com.example.binarchapter8.areaMain.PemainVsCpuPresenter
import com.example.binarchapter8.areaMain.PemainVsPemainPresenter
import com.example.binarchapter8.login.LoginPresenter
import com.example.binarchapter8.main.MenuActivityPresenter
import com.example.binarchapter8.main.ui.battle.BattlePresenter
import com.example.binarchapter8.main.ui.history.HistoryPresenter
import com.example.binarchapter8.main.ui.profile.ProfilePresenter
import com.example.binarchapter8.network.ApiService
import com.example.binarchapter8.register.RegisterPresenter
import com.example.binarchapter8.welcome.PreparePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule {

    @Singleton
    @Provides
    fun provideLoginPresenter(apiService: ApiService): LoginPresenter {
        return LoginPresenter(apiService)
    }

    @Singleton
    @Provides
    fun provideRegisterPresenter(apiService: ApiService): RegisterPresenter {
        return RegisterPresenter(apiService)
    }

    @Singleton
    @Provides
    fun providePemainVsPemainPresenter(apiService: ApiService): PemainVsPemainPresenter {
        return PemainVsPemainPresenter(apiService)
    }

    @Singleton
    @Provides
    fun providePemainVsCpu(apiService: ApiService): PemainVsCpuPresenter {
        return PemainVsCpuPresenter(apiService)
    }

    @Singleton
    @Provides
    fun profilePresenter(apiService: ApiService): ProfilePresenter {
        return ProfilePresenter(apiService)
    }

    @Singleton
    @Provides
    fun provideHistoryPresenter(apiService: ApiService): HistoryPresenter {
        return HistoryPresenter(apiService)
    }

    @Singleton
    @Provides
    fun provideBattlePresenter(): BattlePresenter {
        return BattlePresenter()
    }

    @Singleton
    @Provides
    fun providePreparePresenter(apiService: ApiService): PreparePresenter {
        return PreparePresenter(apiService)
    }

    @Singleton
    @Provides
    fun provideMenuActivityPresenter(): MenuActivityPresenter {
        return MenuActivityPresenter()
    }

}