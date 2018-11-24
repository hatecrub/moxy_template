package com.github.alexeygorovoy.moxytemplate.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.alexeygorovoy.moxytemplate.R
import com.github.alexeygorovoy.moxytemplate.dagger.activity.ActivityComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.support.CompatInjectionManager

abstract class BaseActivity : AppCompatActivity(), IHasComponent<ActivityComponent> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CompatInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    fun replaceToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(BACK_STACK_TAG)
            .commit()
    }

    override fun getComponent() = ActivityComponent()

    companion object {

        private const val BACK_STACK_TAG = "back_stack_tag"
    }
}
