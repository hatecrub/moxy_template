package com.github.alexeygorovoy.moxytemplate.ui.demo.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.alexeygorovoy.moxytemplate.R
import com.github.alexeygorovoy.moxytemplate.dagger.demo.splash.SplashComponent
import com.github.alexeygorovoy.moxytemplate.navigation.Router
import com.github.alexeygorovoy.moxytemplate.ui.common.moxy.BaseMvpFragment
import com.github.alexeygorovoy.moxytemplate.ui.demo.splash.presenter.SplashPresenter
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.support.CompatInjectionManager
import javax.inject.Inject

class SplashFragment : BaseMvpFragment(), SplashView, IHasComponent<SplashComponent> {

    @Inject
    lateinit var router: Router

    @Inject
    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter {
        return splashPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        CompatInjectionManager
            .bindComponent(this)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun openHeroesScreen() {
        val activity = baseActivity
        router.startMainActivity(activity)
        activity.finish()
    }

    override fun getComponent() = SplashComponent()
}
