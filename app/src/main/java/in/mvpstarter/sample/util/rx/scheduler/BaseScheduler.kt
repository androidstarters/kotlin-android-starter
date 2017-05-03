package `in`.mvpstarter.sample.util.rx.scheduler

import org.reactivestreams.Publisher

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.MaybeTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * Created by lam on 2/6/17.
 */

abstract class BaseScheduler<T> protected constructor(private val mSubscribeOnScheduler: Scheduler, private val mObserveOnScheduler: Scheduler) : ObservableTransformer<T, T>, SingleTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer, FlowableTransformer<T, T> {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler)
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler)
    }
}
