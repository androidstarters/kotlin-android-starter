package io.mvpstarter.sample.ui.detail

import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.data.model.Statistic
import io.mvpstarter.sample.ui.base.MvpView

interface DetailMvpView : MvpView {

    fun showPokemon(pokemon: Pokemon)

    fun showStat(statistic: Statistic)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}