package io.mvpstarter.sample.features.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import io.mvpstarter.sample.R
import io.mvpstarter.sample.features.base.BaseActivity
import io.mvpstarter.sample.features.common.ErrorView
import io.mvpstarter.sample.features.detail.DetailActivity
import io.mvpstarter.sample.util.gone
import io.mvpstarter.sample.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, PokemonAdapter.ClickListener, ErrorView.ErrorListener {

    @Inject lateinit var pokemonAdapter: PokemonAdapter
    @Inject lateinit var mainPresenter: MainPresenter

    companion object {
        private val POKEMON_COUNT = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(main_toolbar)
        swipeToRefresh?.apply {
            setProgressBackgroundColorSchemeResource(R.color.primary)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener { mainPresenter.getPokemon(POKEMON_COUNT) }
        }

        pokemonAdapter.setClickListener(this)
        recyclerPokemon?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        viewError?.setErrorListener(this)

        mainPresenter.getPokemon(POKEMON_COUNT)
    }

    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showPokemon(pokemon: List<String>) {
        pokemonAdapter.apply {
            setPokemon(pokemon)
            notifyDataSetChanged()
        }

        recyclerPokemon?.visible()
        swipeToRefresh?.visible()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            if (recyclerPokemon?.visibility == View.VISIBLE && pokemonAdapter.itemCount > 0) {
                swipeToRefresh?.isRefreshing = true
            } else {
                progressBar?.visible()
                recyclerPokemon?.gone()
                swipeToRefresh?.gone()
            }

            viewError?.gone()
        } else {
            swipeToRefresh?.isRefreshing = false
            progressBar?.gone()
        }
    }

    override fun showError(error: Throwable) {
        recyclerPokemon?.gone()
        swipeToRefresh?.gone()
        viewError?.visible()
        Timber.e(error, "There was an error retrieving the pokemon")
    }

    override fun onPokemonClick(pokemon: String) {
        startActivity(DetailActivity.getStartIntent(this, pokemon))
    }

    override fun onReloadData() {
        mainPresenter.getPokemon(POKEMON_COUNT)
    }

}