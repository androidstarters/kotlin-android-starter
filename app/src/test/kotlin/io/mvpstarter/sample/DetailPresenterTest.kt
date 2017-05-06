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

    @Mock lateinit var mMockDetailMvpView: DetailMvpView
    @Mock lateinit var mMockDataManager: DataManager
    private var mDetailPresenter: DetailPresenter? = null

    @JvmField
    @Rule val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mDetailPresenter = DetailPresenter(mMockDataManager)
        mDetailPresenter!!.attachView(mMockDetailMvpView)
    }

    @After
    fun tearDown() {
        mDetailPresenter!!.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonDetailReturnsPokemon() {
        val pokemon = TestDataFactory.makePokemon("id")
        `when`(mMockDataManager.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        mDetailPresenter!!.getPokemon(anyString())

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mMockDetailMvpView).showPokemon(pokemon)
        verify<DetailMvpView>(mMockDetailMvpView, never()).showError(RuntimeException())
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonDetailReturnsError() {
        `when`(mMockDataManager.getPokemon("id"))
                .thenReturn(Single.error<Pokemon>(RuntimeException()))

        mDetailPresenter!!.getPokemon("id")

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
//        verify<DetailMvpView>(mMockDetailMvpView).showError(any(Throwable::class.java))
//        verify<DetailMvpView>(mMockDetailMvpView, never()).showPokemon(any(Pokemon::class.java))
    }

}