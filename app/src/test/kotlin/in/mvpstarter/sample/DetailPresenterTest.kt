package `in`.mvpstarter.sample

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import `in`.mvpstarter.sample.common.TestDataFactory
import `in`.mvpstarter.sample.data.DataManager
import `in`.mvpstarter.sample.data.model.Pokemon
import `in`.mvpstarter.sample.ui.detail.DetailMvpView
import `in`.mvpstarter.sample.ui.detail.DetailPresenter
import `in`.mvpstarter.sample.util.RxSchedulersOverrideRule
import io.reactivex.Single

import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    internal var mMockDetailMvpView: DetailMvpView? = null
    @Mock
    internal var mMockDataManager: DataManager? = null
    private var mDetailPresenter: DetailPresenter? = null

    @Rule
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
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
        `when`(mMockDataManager!!.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        mDetailPresenter!!.getPokemon(anyString())

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mMockDetailMvpView).showPokemon(pokemon)
        verify<DetailMvpView>(mMockDetailMvpView, never()).showError(RuntimeException())
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonDetailReturnsError() {
        `when`(mMockDataManager!!.getPokemon("id"))
                .thenReturn(Single.error<Pokemon>(RuntimeException()))

        mDetailPresenter!!.getPokemon("id")

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mMockDetailMvpView).showError(any(Throwable::class.java))
        verify<DetailMvpView>(mMockDetailMvpView, never()).showPokemon(any(Pokemon::class.java))
    }

}