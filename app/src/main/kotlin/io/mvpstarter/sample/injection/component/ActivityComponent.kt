package io.mvpstarter.sample.injection.component

import io.mvpstarter.sample.injection.PerActivity
import io.mvpstarter.sample.injection.module.ActivityModule
import io.mvpstarter.sample.ui.base.BaseActivity
import io.mvpstarter.sample.ui.detail.DetailActivity
import io.mvpstarter.sample.ui.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)
}
