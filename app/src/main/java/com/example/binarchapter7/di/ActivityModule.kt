package com.example.binarchapter7.di

import com.example.binarchapter7.areaMain.PemainVsCpu
import com.example.binarchapter7.areaMain.PemainVsPemain
import com.example.binarchapter7.login.LoginActivity
import com.example.binarchapter7.main.MenuActivity
import com.example.binarchapter7.main.ui.profile.ProfileFragment
import com.example.binarchapter7.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun menuActivity(): MenuActivity

    @ContributesAndroidInjector
    abstract fun pemainVsPemain(): PemainVsPemain

    @ContributesAndroidInjector
    abstract fun pemainVsCpu(): PemainVsCpu

    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun registerActivity(): RegisterActivity


}