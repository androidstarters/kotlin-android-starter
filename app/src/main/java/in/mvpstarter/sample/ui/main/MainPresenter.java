package in.mvpstarter.sample.ui.main;


import java.util.List;

import javax.inject.Inject;

import in.mvpstarter.sample.data.DataManager;
import in.mvpstarter.sample.injection.ConfigPersistent;
import in.mvpstarter.sample.ui.base.BasePresenter;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private CompositeSubscription mSubscriptions;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscriptions.unsubscribe();
        mSubscriptions = null;
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getMvpView().showProgress(true);
        mSubscriptions.add(mDataManager.getPokemonList(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<String>>() {
                    @Override
                    public void onSuccess(List<String> pokemon) {
                        getMvpView().showProgress(false);
                        getMvpView().showPokemon(pokemon);
                    }

                    @Override
                    public void onError(Throwable error) {
                        getMvpView().showProgress(false);
                        getMvpView().showError();
                        Timber.e(error, "There was an error retrieving the pokemon");
                    }
                }));
    }


}
