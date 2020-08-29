package com.example.binarchapter8.di

import com.example.binarchapter8.main.ui.battle.BattleFragment
import com.example.binarchapter8.main.ui.history.HistoryFragment
import com.example.binarchapter8.main.ui.profile.ProfileFragment
import com.example.binarchapter8.welcome.PrepareFragment
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

    @ContributesAndroidInjector
    abstract fun prepareFragment(): PrepareFragment
}