package com.example.binarchapter7.di

import com.example.binarchapter7.main.ui.battle.BattleFragment
import com.example.binarchapter7.main.ui.history.HistoryFragment
import com.example.binarchapter7.main.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun battleFragment(): BattleFragment

    @ContributesAndroidInjector
    abstract fun historyFragment(): HistoryFragment
}