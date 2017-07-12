package io.mvpstarter.sample

import io.mvpstarter.sample.common.TestDataFactory
import io.mvpstarter.sample.data.DataManager
import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.features.detail.DetailMvpView
import io.mvpstarter.sample.features.detail.DetailPresenter
import io.mvpstarter.sample.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock lateinit var mockDetailMvpView: DetailMvpView
    @Mock lateinit var mockDataManager: DataManager
    private var detailPresenter: DetailPresenter? = null

    @JvmField
    @Rule val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailPresenter = DetailPresenter(mockDataManager)
        detailPresenter?.attachView(mockDetailMvpView)
    }

    @After
    fun tearDown() {
        detailPresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonDetailReturnsPokemon() {
        val pokemon = TestDataFactory.makePokemon("id")
        `when`(mockDataManager.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        detailPresenter?.getPokemon(anyString())

        verify<DetailMvpView>(mockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mockDetailMvpView).showPokemon(pokemon)
        verify<DetailMvpView>(mockDetailMvpView, never()).showError(RuntimeException())
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonDetailReturnsError() {
        `when`(mockDataManager.getPokemon("id"))
                .thenReturn(Single.error<Pokemon>(RuntimeException()))

        detailPresenter?.getPokemon("id")

        verify<DetailMvpView>(mockDetailMvpView, times(2)).showProgress(anyBoolean())
//        verify<DetailMvpView>(mockDetailMvpView).showError(any(Throwable::class.java))
//        verify<DetailMvpView>(mockDetailMvpView, never()).showPokemon(any(Pokemon::class.java))
    }

}