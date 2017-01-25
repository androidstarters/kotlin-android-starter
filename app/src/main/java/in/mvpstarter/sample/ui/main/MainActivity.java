package in.mvpstarter.sample.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.mvpstarter.sample.R;
import in.mvpstarter.sample.ui.base.BaseActivity;
import in.mvpstarter.sample.ui.common.ErrorView;
import in.mvpstarter.sample.ui.detail.DetailActivity;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView, PokemonAdapter.ClickListener,
        ErrorView.ErrorListener {

    private static final int POKEMON_COUNT = 20;

    @Inject
    PokemonAdapter mPokemonAdapter;
    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.recycler_pokemon)
    RecyclerView mPokemonRecycler;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mMainPresenter.attachView(this);

        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mMainPresenter.getPokemon(POKEMON_COUNT));

        mPokemonAdapter.setClickListener(this);
        mPokemonRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPokemonRecycler.setAdapter(mPokemonAdapter);

        mErrorView.setErrorListener(this);

        mMainPresenter.getPokemon(POKEMON_COUNT);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showPokemon(List<String> pokemon) {
        mPokemonAdapter.setPokemon(pokemon);
        mPokemonAdapter.notifyDataSetChanged();

        mPokemonRecycler.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (mPokemonRecycler.getVisibility() == View.VISIBLE
                    && mPokemonAdapter.getItemCount() > 0) {
                mSwipeRefreshLayout.setRefreshing(true);
            } else {
                mProgress.setVisibility(View.VISIBLE);

                mPokemonRecycler.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.GONE);
            }

            mErrorView.setVisibility(View.GONE);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        mPokemonRecycler.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onPokemonClick(String pokemon) {
        startActivity(DetailActivity.getStartIntent(this, pokemon));
    }

    @Override
    public void onReloadData() {
        mMainPresenter.getPokemon(POKEMON_COUNT);
    }
}