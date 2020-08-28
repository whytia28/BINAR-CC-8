package com.example.binarchapter8.di

import android.app.Application
import com.example.binarchapter8.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ActivityModule::class,
        FragmentModule::class,
        PresenterModule::class,
        AndroidSupportInjectionModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApp: BaseApp)
}