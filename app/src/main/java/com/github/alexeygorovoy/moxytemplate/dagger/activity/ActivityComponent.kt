package com.github.alexeygorovoy.moxytemplate.dagger.activity

import com.github.alexeygorovoy.moxytemplate.dagger.app.AppComponent
import com.github.alexeygorovoy.moxytemplate.ui.common.BaseActivity
import dagger.Component
import me.vponomarenko.injectionmanager.support.CompatInjectionManager

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    companion object {
        operator fun invoke(): ActivityComponent =
            DaggerActivityComponent.builder()
                .appComponent(CompatInjectionManager.findComponent())
                .build()
    }
}
