package io.mvpstarter.sample.util

import rx.Observable
import rx.Subscription
import rx.plugins.RxJavaObservableExecutionHook

/**
 * RxJava Observable execution hook that handles updating the active subscription
 * count for a given Espresso RxIdlingResource.
 */
class RxIdlingExecutionHook(private val rxIdlingResource: RxIdlingResource) : RxJavaObservableExecutionHook() {

    override fun <T> onSubscribeStart(
            observableInstance: Observable<out T>?, onSubscribe: Observable.OnSubscribe<T>): Observable.OnSubscribe<T> {
        rxIdlingResource.incrementActiveSubscriptionsCount()
        return super.onSubscribeStart(observableInstance, onSubscribe)
    }

    override fun <T> onSubscribeError(e: Throwable): Throwable {
        rxIdlingResource.decrementActiveSubscriptionsCount()
        return super.onSubscribeError<Any>(e)
    }

    override fun <T> onSubscribeReturn(subscription: Subscription): Subscription {
        rxIdlingResource.decrementActiveSubscriptionsCount()
        return super.onSubscribeReturn<Any>(subscription)
    }
}