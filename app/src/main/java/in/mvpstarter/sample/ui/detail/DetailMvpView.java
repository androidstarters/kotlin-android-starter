package in.mvpstarter.sample.ui.detail;

import in.mvpstarter.sample.data.model.Pokemon;
import in.mvpstarter.sample.data.model.Statistic;
import in.mvpstarter.sample.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);

}