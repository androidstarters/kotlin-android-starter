package in.mvpstarter.sample.ui.main;

import java.util.List;

import in.mvpstarter.sample.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showPokemon(List<String> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);

}