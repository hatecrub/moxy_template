package com.github.alexeygorovoy.moxytemplate.dagger.demo.splash

import com.github.alexeygorovoy.moxytemplate.navigation.Router
import com.github.alexeygorovoy.moxytemplate.utils.rx.RxSchedulers

interface SplashDependencies {
    fun router(): Router
    fun rxSchedulers(): RxSchedulers
}