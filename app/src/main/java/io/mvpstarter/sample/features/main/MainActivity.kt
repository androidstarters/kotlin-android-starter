package io.mvpstarter.sample.features.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import io.mvpstarter.sample.R
import io.mvpstarter.sample.features.base.BaseActivity
import io.mvpstarter.sample.features.common.ErrorView
import io.mvpstarter.sample.features.detail.DetailActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView, PokemonAdapter.ClickListener, ErrorView.ErrorListener {

    @Inject lateinit var pokemonAdapter: PokemonAdapter
    @Inject lateinit var mainPresenter: MainPresenter

    @BindView(R.id.view_error) @JvmField var errorView: ErrorView? = null
    @BindView(R.id.progress) @JvmField var progressBar: ProgressBar? = null
    @BindView(R.id.recycler_pokemon) @JvmField var pokemonRecycler: RecyclerView? = null
    @BindView(R.id.swipe_to_refresh) @JvmField var swipeRefreshLayout: SwipeRefreshLayout? = null
    @BindView(R.id.toolbar) @JvmField var toolbar: Toolbar? = null

    companion object {
        private val POKEMON_COUNT = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(toolbar)
w
        swipeRefreshLayout?.apply {
            setProgressBackgroundColorSchemeResource(R.color.primary)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener { mainPresenter.getPokemon(POKEMON_COUNT) }
        }

        pokemonAdapter.setClickListener(this)
        pokemonRecycler?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        errorView?.setErrorListener(this)

        mainPresenter.getPokemon(POKEMON_COUNT)
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showPokemon(pokemon: List<String>) {
        pokemonAdapter.apply {
            setPokemon(pokemon)
            notifyDataSetChanged()
        }

        pokemonRecycler?.visibility = View.VISIBLE
        swipeRefreshLayout?.visibility = View.VISIBLE
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            if (pokemonRecycler?.visibility == View.VISIBLE && pokemonAdapter.itemCount > 0) {
                swipeRefreshLayout?.isRefreshing = true
            } else {
                progressBar?.visibility = View.VISIBLE

                pokemonRecycler?.visibility = View.GONE
                swipeRefreshLayout?.visibility = View.GONE
            }

            errorView?.visibility = View.GONE
        } else {
            swipeRefreshLayout?.isRefreshing = false
            progressBar?.visibility = View.GONE
        }
    }

    override fun showError(error: Throwable) {
        pokemonRecycler?.visibility = View.GONE
        swipeRefreshLayout?.visibility = View.GONE
        errorView?.visibility = View.VISIBLE
        Timber.e(error, "There was an error retrieving the pokemon")
    }

    override fun onPokemonClick(pokemon: String) {
        startActivity(DetailActivity.getStartIntent(this, pokemon))
    }

    override fun onReloadData() {
        mainPresenter.getPokemon(POKEMON_COUNT)
    }

}