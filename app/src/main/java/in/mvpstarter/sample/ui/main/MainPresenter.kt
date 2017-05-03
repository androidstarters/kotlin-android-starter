package `in`.mvpstarter.sample.ui.main

import javax.inject.Inject

import `in`.mvpstarter.sample.data.DataManager
import `in`.mvpstarter.sample.injection.ConfigPersistent
import `in`.mvpstarter.sample.ui.base.BasePresenter
import `in`.mvpstarter.sample.util.rx.scheduler.SchedulerUtils

@ConfigPersistent
class MainPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

    override fun attachView(mvpView: MainMvpView) {
        super.attachView(mvpView)
    }

    fun getPokemon(limit: Int) {
        checkViewAttached()
        mvpView!!.showProgress(true)
        mDataManager.getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain<List<String>>())
                .subscribe({ pokemons ->
                    mvpView!!.showProgress(false)
                    mvpView!!.showPokemon(pokemons)
                }) { throwable ->
                    mvpView!!.showProgress(false)
                    mvpView!!.showError(throwable)
                }
    }

}