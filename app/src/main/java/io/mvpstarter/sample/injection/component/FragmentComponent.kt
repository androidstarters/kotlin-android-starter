package io.mvpstarter.sample.injection.component

import io.mvpstarter.sample.injection.PerFragment
import io.mvpstarter.sample.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent