package io.mvpstarter.sample.util

import androidx.test.espresso.IdlingResource
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger

/**
 * Espresso Idling resource that handles waiting for RxJava Observables executions.
 * This class must be used with RxIdlingExecutionHook.
 * Before registering this idling resource you must:
 * 1. Create an instance of RxIdlingExecutionHook by passing an instance of this class.
 * 2. Register RxIdlingExecutionHook with the RxJavaPlugins using registerObservableExecutionHook()
 * 3. Register this idle resource with Espresso using Espresso.registerIdlingResources()
 */
class RxIdlingResource : IdlingResource {

    private val activeSubscriptionsCount = AtomicInteger(0)
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return javaClass.simpleName
    }

    override fun isIdleNow(): Boolean {
        return activeSubscriptionsCount.get() <= 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    fun incrementActiveSubscriptionsCount() {
        val count = activeSubscriptionsCount.incrementAndGet()
        Timber.i("Active subscriptions count increased to %d", count)
    }

    fun decrementActiveSubscriptionsCount() {
        val count = activeSubscriptionsCount.decrementAndGet()
        Timber.i("Active subscriptions count decreased to %d", count)
        if (isIdleNow) {
            Timber.i("There is no active subscriptions, transitioning to Idle")
            resourceCallback?.onTransitionToIdle()
        }
    }

}