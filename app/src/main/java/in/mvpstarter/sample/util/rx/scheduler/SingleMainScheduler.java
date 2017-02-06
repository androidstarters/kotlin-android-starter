package in.mvpstarter.sample.util.rx.scheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lam on 2/6/17.
 */

public class SingleMainScheduler<T> extends BaseScheduler<T> {

    protected SingleMainScheduler() {
        super(Schedulers.single(), AndroidSchedulers.mainThread());
    }
}
