package com.github.alexeygorovoy.moxytemplate.dagger.demo.heroes

import com.github.alexeygorovoy.moxytemplate.api.HeroApi
import com.github.alexeygorovoy.moxytemplate.navigation.Router
import com.github.alexeygorovoy.moxytemplate.utils.rx.RxSchedulers

interface HeroesDependencies {
    fun rxSchedulers(): RxSchedulers
    fun heroApi(): HeroApi
    fun router(): Router
}