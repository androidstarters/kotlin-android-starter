package io.mvpstarter.sample.common.injection.component

import dagger.Component
import io.mvpstarter.sample.common.injection.module.ApplicationTestModule
import io.mvpstarter.sample.injection.component.AppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : AppComponent