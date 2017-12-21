package io.mvpstarter.sample.injection.module

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import io.mvpstarter.sample.injection.ActivityContext

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    internal fun providesFragment(): Fragment = fragment

    @Provides
    internal fun provideActivity(): FragmentActivity? = fragment.activity

    @Provides
    @ActivityContext
    internal fun providesContext(): Context? = fragment.context

}