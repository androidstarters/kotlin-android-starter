package `in`.mvpstarter.sample.common.injection.component

import javax.inject.Singleton

import dagger.Component
import `in`.mvpstarter.sample.common.injection.module.ApplicationTestModule
import `in`.mvpstarter.sample.injection.component.ApplicationComponent

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent