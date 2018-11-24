package com.github.alexeygorovoy.moxytemplate.dagger.app

import android.content.Context
import com.github.alexeygorovoy.moxytemplate.api.HeroApi
import com.github.alexeygorovoy.moxytemplate.dagger.demo.heroes.HeroesDependencies
import com.github.alexeygorovoy.moxytemplate.dagger.demo.splash.SplashDependencies
import dagger.Component

@AppScope
@Component(modules = [NetworkModule::class, AppContextModule::class, UtilsModule::class])
interface AppComponent : SplashDependencies, HeroesDependencies {

    fun apiService(): HeroApi

    companion object {
        operator fun invoke(context: Context): AppComponent =
            DaggerAppComponent.builder()
                .appContextModule(AppContextModule(context))
                .build()
    }
}
