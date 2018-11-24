package com.github.alexeygorovoy.moxytemplate.dagger.demo.splash

import com.github.alexeygorovoy.moxytemplate.ui.demo.splash.view.SplashFragment
import dagger.Component
import me.vponomarenko.injectionmanager.support.CompatInjectionManager

@Component(modules = [SplashModule::class], dependencies = [SplashDependencies::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)

    companion object {
        operator fun invoke(): SplashComponent = DaggerSplashComponent.builder()
            .splashDependencies(CompatInjectionManager.findComponent())
            .build()
    }
}
