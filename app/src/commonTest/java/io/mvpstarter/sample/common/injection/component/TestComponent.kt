package io.mvpstarter.sample.common.injection.component

import io.mvpstarter.sample.common.injection.module.ApplicationTestModule
import io.mvpstarter.sample.injection.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent