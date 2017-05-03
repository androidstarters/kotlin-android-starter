package `in`.mvpstarter.sample.injection.component

import `in`.mvpstarter.sample.injection.ConfigPersistent
import `in`.mvpstarter.sample.injection.module.ActivityModule
import `in`.mvpstarter.sample.injection.module.FragmentModule
import `in`.mvpstarter.sample.ui.base.BaseActivity
import `in`.mvpstarter.sample.ui.base.BaseFragment
import dagger.Component

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check [BaseActivity] and [BaseFragment] to
 * see how this components survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

}
