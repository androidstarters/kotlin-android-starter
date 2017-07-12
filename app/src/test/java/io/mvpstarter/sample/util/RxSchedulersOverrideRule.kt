package io.mvpstarter.sample.util


import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Callable

/**
 * NOTE: You MUST use this rule in every test class that targets app code that uses RxJava.
 * Even when that code doesn't use any scheduler. The RxJava [Schedulers] class is setup
 * once and caches the schedulers in memory. So if one of the test classes doesn't use this rule
 * by the time this rule runs it may be too late to override the schedulers. This is really not
 * ideal but currently there isn't a better approach.
 * More info here: https://github.com/ReactiveX/RxJava/issues/2297
 *
 *
 * This rule registers SchedulerHooks for RxJava and RxAndroid to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.immediate().
 * Warning, this rule will reset RxAndroidPlugins and RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
class RxSchedulersOverrideRule : TestRule {

    private val SCHEDULER_INSTANCE = Schedulers.trampoline()
    private val schedulerFunction = Function<Scheduler, Scheduler> { SCHEDULER_INSTANCE }
    private val schedulerFunctionLazy = Function<Callable<Scheduler>, Scheduler> { SCHEDULER_INSTANCE }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction)

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()

            }
        }
    }
}