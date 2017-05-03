package `in`.mvpstarter.sample.ui.detail

import `in`.mvpstarter.sample.data.model.Pokemon
import `in`.mvpstarter.sample.data.model.Statistic
import `in`.mvpstarter.sample.ui.base.MvpView

interface DetailMvpView : MvpView {

    fun showPokemon(pokemon: Pokemon)

    fun showStat(statistic: Statistic)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}