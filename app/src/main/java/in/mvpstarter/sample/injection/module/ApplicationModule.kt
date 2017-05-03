package `in`.mvpstarter.sample.injection.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import `in`.mvpstarter.sample.data.remote.MvpStarterService
import `in`.mvpstarter.sample.data.remote.MvpStarterServiceFactory
import `in`.mvpstarter.sample.injection.ApplicationContext

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideMvpStarterService(): MvpStarterService {
        return MvpStarterServiceFactory.makeStarterService()
    }
}
