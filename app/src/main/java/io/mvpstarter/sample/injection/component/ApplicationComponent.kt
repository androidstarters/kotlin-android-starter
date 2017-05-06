package io.mvpstarter.sample.injection.component

import io.mvpstarter.sample.data.DataManager
import io.mvpstarter.sample.data.remote.MvpStarterService
import io.mvpstarter.sample.injection.ApplicationContext
import io.mvpstarter.sample.injection.module.ApplicationModule
import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun mvpBoilerplateService(): MvpStarterService
}
