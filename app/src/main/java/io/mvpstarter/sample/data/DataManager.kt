package io.mvpstarter.sample.data

import io.mvpstarter.sample.annotation.OpenClassOnDebug
import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.data.remote.PokemonApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@OpenClassOnDebug
@Singleton
class DataManager @Inject
constructor(private val pokemonApi: PokemonApi) {

    fun getPokemonList(limit: Int): Single<List<String>> {
        return pokemonApi.getPokemonList(limit)
                .toObservable()
                .flatMapIterable { (results) -> results }
                .map { (name) -> name }
                .toList()
    }

    fun getPokemon(name: String): Single<Pokemon> {
        return pokemonApi.getPokemon(name)
    }
}