package com.example.binarchapter8.di

import com.example.binarchapter8.areaMain.PemainVsCpu
import com.example.binarchapter8.areaMain.PemainVsPemain
import com.example.binarchapter8.login.LoginActivity
import com.example.binarchapter8.main.MenuActivity
import com.example.binarchapter8.register.RegisterActivity
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