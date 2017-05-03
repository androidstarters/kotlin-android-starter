package `in`.mvpstarter.sample.injection.module

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import `in`.mvpstarter.sample.injection.ActivityContext

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }
}
