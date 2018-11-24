package com.github.alexeygorovoy.moxytemplate.dagger.demo.heroes

import com.github.alexeygorovoy.moxytemplate.ui.demo.heroes.view.HeroesListFragment
import dagger.Component
import me.vponomarenko.injectionmanager.support.CompatInjectionManager

@Component(modules = [HeroesModule::class], dependencies = [HeroesDependencies::class])
interface HeroesComponent {

    fun inject(fragment: HeroesListFragment)

    companion object {
        operator fun invoke(): HeroesComponent = DaggerHeroesComponent.builder()
            .heroesDependencies(CompatInjectionManager.findComponent())
            .heroesModule(HeroesModule())
            .build()
    }
}
