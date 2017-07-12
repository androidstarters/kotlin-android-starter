package io.mvpstarter.sample

import io.mvpstarter.sample.common.TestDataFactory
import io.mvpstarter.sample.data.DataManager
import io.mvpstarter.sample.data.model.PokemonListResponse
import io.mvpstarter.sample.data.remote.PokemonApi
import io.mvpstarter.sample.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule @JvmField val overrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock lateinit var mockPokemonApi: PokemonApi

    private var dataManager: DataManager? = null

    @Before
    fun setUp() {
        dataManager = DataManager(mockPokemonApi)
    }

    @Test
    fun getPokemonListCompletesAndEmitsPokemonList() {
        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
        val pokemonListResponse = PokemonListResponse(namedResourceList)

        `when`(mockPokemonApi.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse))

        dataManager?.getPokemonList(10)
                ?.test()
                ?.assertComplete()
                ?.assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        val name = "charmander"
        val pokemon = TestDataFactory.makePokemon(name)
        `when`(mockPokemonApi.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        dataManager?.getPokemon(name)
                ?.test()
                ?.assertComplete()
                ?.assertValue(pokemon)
    }
}
