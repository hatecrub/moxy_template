package com.github.alexeygorovoy.moxytemplate.ui.demo.heroes.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.alexeygorovoy.moxytemplate.R
import com.github.alexeygorovoy.moxytemplate.api.models.Hero
import com.github.alexeygorovoy.moxytemplate.dagger.demo.heroes.HeroesComponent
import com.github.alexeygorovoy.moxytemplate.navigation.Router
import com.github.alexeygorovoy.moxytemplate.ui.common.moxy.BaseMvpFragment
import com.github.alexeygorovoy.moxytemplate.ui.demo.heroes.presenter.HeroesListPresenter
import com.github.alexeygorovoy.moxytemplate.ui.demo.heroes.view.adapter.HeroesAdapter
import kotlinx.android.synthetic.main.fragment_heroes_list.*
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.support.CompatInjectionManager
import javax.inject.Inject

class HeroesListFragment : BaseMvpFragment(), HeroesListView, IHasComponent<HeroesComponent> {

    @Inject
    lateinit var router: Router

    @Inject
    @InjectPresenter
    lateinit var presenter: HeroesListPresenter

    private var adapter: HeroesAdapter = HeroesAdapter()

    @ProvidePresenter
    fun providePresenter(): HeroesListPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        CompatInjectionManager
            .bindComponent(this)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_heroes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onHeroClickedObservable()
            .subscribe { hero -> router.openHeroDetails(baseActivity, hero) }
            .unsubscribeOnDestroyView()

        heroesList.adapter = adapter
        val mLayoutManager = LinearLayoutManager(requireContext())
        heroesList.layoutManager = mLayoutManager
    }

    override fun showHeroes(heroes: List<Hero>) {
        adapter.setItems(heroes)
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun getComponent() = HeroesComponent()

    companion object {

        fun newInstance(): HeroesListFragment {

            val args = Bundle()

            val fragment = HeroesListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
