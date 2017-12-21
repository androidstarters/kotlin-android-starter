package io.mvpstarter.sample.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import io.mvpstarter.sample.data.DataManager
import io.mvpstarter.sample.data.remote.PokemonApi
import io.mvpstarter.sample.injection.ApplicationContext
import io.mvpstarter.sample.injection.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun pokemonApi(): PokemonApi
}
