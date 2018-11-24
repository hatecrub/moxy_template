package com.github.alexeygorovoy.moxytemplate.dagger.demo.details

import com.github.alexeygorovoy.moxytemplate.ui.demo.details.view.HeroDetailsFragment
import dagger.Component

@Component(modules = [HeroDetailsModule::class])
interface HeroDetailsComponent {
    fun inject(fragment: HeroDetailsFragment)

    companion object {
        operator fun invoke(): HeroDetailsComponent = DaggerHeroDetailsComponent.builder()
            .build()
    }
}
