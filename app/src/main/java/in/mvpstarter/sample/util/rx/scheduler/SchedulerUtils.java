package in.mvpstarter.sample.util.rx.scheduler;

/**
 * Created by lam on 2/6/17.
 */

public class SchedulerUtils {

    public static <T> IoMainScheduler<T> ioToMain() {
        return new IoMainScheduler<>();
    }
}
