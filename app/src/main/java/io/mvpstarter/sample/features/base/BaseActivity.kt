package io.mvpstarter.sample.features.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.collection.LongSparseArray
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import butterknife.ButterKnife
import io.mvpstarter.sample.MvpStarterApplication
import io.mvpstarter.sample.injection.component.ActivityComponent
import io.mvpstarter.sample.injection.component.ConfigPersistentComponent
import io.mvpstarter.sample.injection.component.DaggerConfigPersistentComponent
import io.mvpstarter.sample.injection.module.ActivityModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Abstract activity that every other Activity in this application must implement. It provides the
 * following functionality:
 * - Handles creation of Dagger components and makes sure that instances of
 * ConfigPersistentComponent are kept across configuration changes.
 * - Set up and handles a GoogleApiClient instance that can be used to access the Google sign in
 * api.
 * - Handles signing out when an authentication error event is received.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null
    private var activityId = 0L

    companion object {
        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val componentsArray = LongSparseArray<ConfigPersistentComponent>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ButterKnife.bind(this)
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (componentsArray.get(activityId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(MvpStarterApplication[this].component)
                    .build()
            componentsArray.put(activityId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = componentsArray.get(activityId)!!
        }
        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        activityComponent?.inject(this)
    }

    @LayoutRes abstract fun layoutId(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
            componentsArray.remove(activityId)
        }
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun activityComponent() = activityComponent as ActivityComponent
}