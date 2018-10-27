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

    private val random = Random()

    private fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun makePokemon(id: String): Pokemon {
        return Pokemon(id, randomUuid() + id, makeSprites(), makeStatisticList(3))
    }

    fun makePokemonNamesList(count: Int): List<String> {
        return (0 until count).mapTo(ArrayList()) { makePokemon(it.toString()).name }
    }

    fun makePokemonNameList(pokemonList: List<NamedResource>): List<String> {
        return pokemonList.mapTo(ArrayList()) { it.name }
    }

    private fun makeStatistic(): Statistic {
        val statistic = Statistic()
        statistic.baseStat = random.nextInt()
        statistic.stat = makeNamedResource(randomUuid())
        return statistic
    }

    private fun makeStatisticList(count: Int): List<Statistic> {
        val statisticList = ArrayList<Statistic>()
        for (i in 0 until count) {
            statisticList.add(makeStatistic())
        }
        return statisticList
    }

    private fun makeSprites(): Sprites {
        val sprites = Sprites()
        sprites.frontDefault = randomUuid()
        return sprites
    }

    private fun makeNamedResource(unique: String): NamedResource {
        return NamedResource(randomUuid() + unique, randomUuid())
    }

    fun makeNamedResourceList(count: Int): List<NamedResource> {
        return (0 until count).map { makeNamedResource(it.toString()) }
    }
}