package io.mvpstarter.sample

import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.mvpstarter.sample.common.TestComponentRule
import io.mvpstarter.sample.common.TestDataFactory
import io.mvpstarter.sample.data.model.Pokemon
import io.mvpstarter.sample.features.main.MainActivity
import io.mvpstarter.sample.util.ErrorTestUtil
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val component = TestComponentRule(ApplicationProvider.getApplicationContext())
    private val main = ActivityTestRule(MainActivity::class.java, false, false)

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule @JvmField
    var chain: TestRule = RuleChain.outerRule(component).around(main)

    @Test
    fun checkPokemonsDisplay() {
        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
        val pokemonList = TestDataFactory.makePokemonNameList(namedResourceList)
        stubDataManagerGetPokemonList(Single.just(pokemonList))
        main.launchActivity(null)

        for (pokemonName in namedResourceList) {
            onView(withText(pokemonName.name))
                    .check(matches(isDisplayed()))
        }
    }

    @Test
    fun clickingPokemonLaunchesDetailActivity() {
        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
        val pokemonList = TestDataFactory.makePokemonNameList(namedResourceList)
        stubDataManagerGetPokemonList(Single.just(pokemonList))
        stubDataManagerGetPokemon(Single.just(TestDataFactory.makePokemon("id")))
        main.launchActivity(null)

        onView(withText(pokemonList[0]))
                .perform(click())

        onView(withId(R.id.imagePokemon))
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorViewDisplays() {
        stubDataManagerGetPokemonList(Single.error(RuntimeException()))
        main.launchActivity(null)
        ErrorTestUtil.checkErrorViewsDisplay()
    }

    fun stubDataManagerGetPokemonList(single: Single<List<String>>) {
        `when`(component.mockDataManager.getPokemonList(ArgumentMatchers.anyInt()))
                .thenReturn(single)
    }

    fun stubDataManagerGetPokemon(single: Single<Pokemon>) {
        `when`(component.mockDataManager.getPokemon(ArgumentMatchers.anyString()))
                .thenReturn(single)
    }

}