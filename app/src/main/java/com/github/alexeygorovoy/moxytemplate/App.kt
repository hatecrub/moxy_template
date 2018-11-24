package com.github.alexeygorovoy.moxytemplate

import android.app.Application
import com.github.alexeygorovoy.moxytemplate.dagger.app.AppComponent
import com.squareup.leakcanary.LeakCanary
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.support.CompatInjectionManager
import timber.log.Timber

class App : Application(), IHasComponent<AppComponent> {

    override fun onCreate() {
        super.onCreate()

        CompatInjectionManager.init(this)

        CompatInjectionManager.bindComponent(this)

        initialiseLogger()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    override fun getComponent() = AppComponent(this)

    private fun initialiseLogger() {
        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
                    //TODO  decide what to log in release version
                }
            })
        }
    }
}
