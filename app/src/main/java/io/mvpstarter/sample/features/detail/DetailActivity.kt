package io.mvpstarter.sample.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import butterknife.BindView
import io.mvpstarter.sample.R
import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.data.model.Statistic
import io.mvpstarter.sample.features.base.BaseActivity
import io.mvpstarter.sample.features.common.ErrorView
import io.mvpstarter.sample.features.detail.widget.StatisticView
import io.mvpstarter.sample.util.loadImageFromUrl
import timber.log.Timber
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailMvpView, ErrorView.ErrorListener {

    @Inject lateinit var detailPresenter: DetailPresenter

    @BindView(R.id.view_error) @JvmField var errorView: ErrorView? = null
    @BindView(R.id.image_pokemon) @JvmField var pokemonImage: ImageView? = null
    @BindView(R.id.progress) @JvmField var progressBar: ProgressBar? = null
    @BindView(R.id.toolbar) @JvmField var toolbar: Toolbar? = null
    @BindView(R.id.layout_stats) @JvmField var statLayout: LinearLayout? = null
    @BindView(R.id.layout_pokemon) @JvmField var pokemonLayout: View? = null

    private var pokemonName: String? = null

    companion object {
        val EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME"

        fun getStartIntent(context: Context, pokemonName: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON_NAME, pokemonName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        detailPresenter.attachView(this)

        pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME)
        if (pokemonName == null) {
            throw IllegalArgumentException("Detail Activity requires a pokemon name@")
        }

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = pokemonName?.substring(0, 1)?.toUpperCase() + pokemonName?.substring(1)

        errorView?.setErrorListener(this)

        detailPresenter.getPokemon(pokemonName as String)
    }

    override val layout: Int
        get() = R.layout.activity_detail

    override fun showPokemon(pokemon: Pokemon) {
        if (pokemon.sprites.frontDefault != null) {
            pokemonImage?.loadImageFromUrl(pokemon.sprites.frontDefault as String)
        }
        pokemonLayout?.visibility = View.VISIBLE
    }

    override fun showStat(statistic: Statistic) {
        val statisticView = StatisticView(this)
        statisticView.setStat(statistic)
        statLayout?.addView(statisticView)
    }

    override fun showProgress(show: Boolean) {
        errorView?.visibility = View.GONE
        progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(error: Throwable) {
        pokemonLayout?.visibility = View.GONE
        errorView?.visibility = View.VISIBLE
        Timber.e(error, "There was a problem retrieving the pokemon...")
    }

    override fun onReloadData() {
        detailPresenter.getPokemon(pokemonName as String)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.detachView()
    }

}