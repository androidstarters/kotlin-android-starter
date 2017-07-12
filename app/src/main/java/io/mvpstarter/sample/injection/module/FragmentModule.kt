package io.mvpstarter.sample.injection.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import io.mvpstarter.sample.injection.ActivityContext

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    internal fun providesFragment(): Fragment {
        return fragment
    }

    @Provides
    internal fun provideActivity(): Activity {
        return fragment.activity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return fragment.activity
    }

}