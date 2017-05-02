package `in`.mvpstarter.sample

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import `in`.mvpstarter.sample.common.TestDataFactory
import `in`.mvpstarter.sample.data.DataManager
import `in`.mvpstarter.sample.data.model.PokemonListResponse
import `in`.mvpstarter.sample.data.remote.MvpStarterService
import `in`.mvpstarter.sample.util.RxSchedulersOverrideRule
import io.reactivex.Single

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock
    internal var mMockMvpStarterService: MvpStarterService? = null
    private var mDataManager: DataManager? = null

    @Before
    fun setUp() {
        mDataManager = DataManager(mMockMvpStarterService)
    }

    @Test
    fun getPokemonListCompletesAndEmitsPokemonList() {
        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
        val pokemonListResponse = PokemonListResponse()
        pokemonListResponse.results = namedResourceList

        `when`(mMockMvpStarterService!!.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse))

        mDataManager!!.getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        val name = "charmander"
        val pokemon = TestDataFactory.makePokemon(name)
        `when`(mMockMvpStarterService!!.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        mDataManager!!.getPokemon(name)
                .test()
                .assertComplete()
                .assertValue(pokemon)
    }
}
