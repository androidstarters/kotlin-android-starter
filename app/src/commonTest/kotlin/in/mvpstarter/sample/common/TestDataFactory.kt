package `in`.mvpstarter.sample.common

import java.util.ArrayList
import java.util.Random
import java.util.UUID

import `in`.mvpstarter.sample.data.model.NamedResource
import `in`.mvpstarter.sample.data.model.Pokemon
import `in`.mvpstarter.sample.data.model.Sprites
import `in`.mvpstarter.sample.data.model.Statistic

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
        val pokemon = Pokemon()
        pokemon.id = id
        pokemon.name = randomUuid() + id
        pokemon.stats = makeStatisticList(3)
        pokemon.sprites = makeSprites()
        return pokemon
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
        val namedResource = NamedResource()
        namedResource.name = randomUuid() + unique
        namedResource.url = randomUuid()
        return namedResource
    }

    fun makeNamedResourceList(count: Int): List<NamedResource> {
        val namedResourceList = ArrayList<NamedResource>()
        for (i in 0..count - 1) {
            namedResourceList.add(makeNamedResource(i.toString()))
        }
        return namedResourceList
    }
}