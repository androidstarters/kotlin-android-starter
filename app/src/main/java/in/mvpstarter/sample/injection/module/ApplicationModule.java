package in.mvpstarter.sample.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.mvpstarter.sample.data.remote.MvpStarterService;
import in.mvpstarter.sample.data.remote.MvpStarterServiceFactory;
import in.mvpstarter.sample.injection.ApplicationContext;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    static MvpStarterService provideMvpStarterService() {
        return MvpStarterServiceFactory.makeStarterService();
    }
}
