package in.mvpstarter.sample.injection.component;

import dagger.Subcomponent;
import in.mvpstarter.sample.injection.PerActivity;
import in.mvpstarter.sample.injection.module.ActivityModule;
import in.mvpstarter.sample.ui.base.BaseActivity;
import in.mvpstarter.sample.ui.detail.DetailActivity;
import in.mvpstarter.sample.ui.main.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
