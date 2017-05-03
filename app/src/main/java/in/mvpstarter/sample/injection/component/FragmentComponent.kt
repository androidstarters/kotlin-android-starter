package `in`.mvpstarter.sample.injection.component

import `in`.mvpstarter.sample.injection.PerFragment
import `in`.mvpstarter.sample.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent