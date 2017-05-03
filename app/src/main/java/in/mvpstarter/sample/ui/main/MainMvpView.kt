package `in`.mvpstarter.sample.ui.main

import `in`.mvpstarter.sample.ui.base.MvpView

interface MainMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}