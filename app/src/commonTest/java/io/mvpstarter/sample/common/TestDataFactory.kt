package io.mvpstarter.sample.common

import io.mvpstarter.sample.data.model.NamedResource
import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.data.model.Sprites
import io.mvpstarter.sample.data.model.Statistic
import java.util.*

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
object TestDataFactory {

    private val sRandom = Random()

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun makePokemon(id: String): Pokemon {
        return Pokemon(id, randomUuid() + id, makeSprites(), makeStatisticList(3))
    }

    fun makePokemonNamesList(count: Int): List<String> {
        val pokemonList = ArrayList<String>()
        for (i in 0..count - 1) {
            pokemonList.add(makePokemon(i.toString()).name)
        }
        return pokemonList
    }

    fun makePokemonNameList(pokemonList: List<NamedResource>): List<String> {
        val names = ArrayList<String>()
        for (pokemon in pokemonList) {
            names.add(pokemon.name)
        }
        return names
    }

    fun makeStatistic(): Statistic {
        val statistic = Statistic()
        statistic.baseStat = sRandom.nextInt()
        statistic.stat = makeNamedResource(randomUuid())
        return statistic
    }

    fun makeStatisticList(count: Int): List<Statistic> {
        val statisticList = ArrayList<Statistic>()
        for (i in 0..count - 1) {
            statisticList.add(makeStatistic())
        }
        return statisticList
    }

    fun makeSprites(): Sprites {
        val sprites = Sprites()
        sprites.frontDefault = randomUuid()
        return sprites
    }

    fun makeNamedResource(unique: String): NamedResource {
        return NamedResource(randomUuid() + unique, randomUuid())
    }

    fun makeNamedResourceList(count: Int): List<NamedResource> {
        val namedResourceList = (0..count - 1).map { makeNamedResource(it.toString()) }
        return namedResourceList
    }
}