package `in`.mvpstarter.sample.util

import org.junit.runner.Description
import org.junit.runners.model.Statement

import java.util.concurrent.Callable

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

/**
 * Created by ravindra on 03/05/17.
 */

class RxSchedulersOverrideRule {

    private val SCHEDULER_INSTANCE = Schedulers.trampoline()

    private val schedulerFunction = Function<Scheduler, Scheduler> { SCHEDULER_INSTANCE }

    private val schedulerFunctionLazy = Function<Callable<Scheduler>, Scheduler> { SCHEDULER_INSTANCE }

    fun apply(base: Statement, description: Description): Statement {
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

