package in.mvpstarter.sample.util;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/**
 * NOTE: You MUST use this rule in every test class that targets app code that uses RxJava.
 * Even when that code doesn't use any scheduler. The RxJava {@link Schedulers} class is setup
 * once and caches the schedulers in memory. So if one of the test classes doesn't use this rule
 * by the time this rule runs it may be too late to override the schedulers. This is really not
 * ideal but currently there isn't a better approach.
 * More info here: https://github.com/ReactiveX/RxJava/issues/2297
 * <p>
 * This rule registers SchedulerHooks for RxJava and RxAndroid to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.immediate().
 * Warning, this rule will reset RxAndroidPlugins and RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
public class RxSchedulersOverrideRule implements TestRule {

    private final RxAndroidSchedulersHook mRxAndroidSchedulersHook = new RxAndroidSchedulersHook() {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    private final Func1<Scheduler, Scheduler> mRxJavaSchedulerHook = scheduler
            -> Schedulers.immediate();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.getInstance().reset();
                RxAndroidPlugins.getInstance().registerSchedulersHook(mRxAndroidSchedulersHook);

                RxJavaHooks.reset();
                RxJavaHooks.setOnIOScheduler(mRxJavaSchedulerHook);
                RxJavaHooks.setOnNewThreadScheduler(mRxJavaSchedulerHook);
                RxJavaHooks.setOnComputationScheduler(mRxJavaSchedulerHook);

                base.evaluate();

                RxAndroidPlugins.getInstance().reset();
                RxJavaHooks.reset();
            }
        };
    }
}