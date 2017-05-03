package `in`.mvpstarter.sample.common.injection.component

import `in`.mvpstarter.sample.common.injection.module.ApplicationTestModule
import `in`.mvpstarter.sample.injection.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent