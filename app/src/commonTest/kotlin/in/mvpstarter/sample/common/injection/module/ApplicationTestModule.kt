package `in`.mvpstarter.sample.common.injection.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import `in`.mvpstarter.sample.data.DataManager
import `in`.mvpstarter.sample.data.remote.MvpStarterService
import `in`.mvpstarter.sample.injection.ApplicationContext

import org.mockito.Mockito.mock

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
class ApplicationTestModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    /*************
     * MOCKS
     */

    @Provides
    @Singleton
    internal fun providesDataManager(): DataManager {
        return mock(DataManager::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMvpBoilerplateService(): MvpStarterService {
        return mock(MvpStarterService::class.java)
    }

}
