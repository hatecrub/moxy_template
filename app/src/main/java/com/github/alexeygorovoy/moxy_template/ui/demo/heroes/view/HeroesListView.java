package com.github.alexeygorovoy.moxy_template.ui.demo.heroes.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.alexeygorovoy.moxy_template.api.models.Hero;
import com.github.alexeygorovoy.moxy_template.ui.common.moxy.BaseMvpView;
import com.github.alexeygorovoy.moxy_template.ui.common.moxy.MvpProgressView;

import java.util.List;

public interface HeroesListView extends BaseMvpView, MvpProgressView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHeroes(List<Hero> heroes);

}