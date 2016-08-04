package in.mvpstarter.sample.injection.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import in.mvpstarter.sample.injection.ActivityContext;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment providesFragment() {
        return mFragment;
    }

    @Provides
    Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mFragment.getActivity();
    }

}