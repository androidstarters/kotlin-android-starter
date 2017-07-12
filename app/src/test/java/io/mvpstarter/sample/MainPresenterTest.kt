package io.mvpstarter.sample

import io.mvpstarter.sample.common.TestDataFactory
import io.mvpstarter.sample.data.DataManager
import io.mvpstarter.sample.features.main.MainMvpView
import io.mvpstarter.sample.features.main.MainPresenter
import io.mvpstarter.sample.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock lateinit var mockMainMvpView: MainMvpView
    @Mock lateinit var mockDataManager: DataManager
    private var mainPresenter: MainPresenter? = null

    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        mainPresenter = MainPresenter(mockDataManager)
        mainPresenter?.attachView(mockMainMvpView)
    }

    @After
    fun tearDown() {
        mainPresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsPokemonNames() {
        val pokemonList = TestDataFactory.makePokemonNamesList(10)
        `when`(mockDataManager.getPokemonList(10))
                .thenReturn(Single.just(pokemonList))

        mainPresenter?.getPokemon(10)

        verify<MainMvpView>(mockMainMvpView, times(2)).showProgress(anyBoolean())
        verify<MainMvpView>(mockMainMvpView).showPokemon(pokemonList)
        verify<MainMvpView>(mockMainMvpView, never()).showError(RuntimeException())

    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsError() {
        `when`(mockDataManager.getPokemonList(10))
                .thenReturn(Single.error<List<String>>(RuntimeException()))

        mainPresenter?.getPokemon(10)

        verify<MainMvpView>(mockMainMvpView, times(2)).showProgress(anyBoolean())
//        verify<MainMvpView>(mockMainMvpView).showError(RuntimeException())
        verify<MainMvpView>(mockMainMvpView, never()).showPokemon(ArgumentMatchers.anyList<String>())
    }

}