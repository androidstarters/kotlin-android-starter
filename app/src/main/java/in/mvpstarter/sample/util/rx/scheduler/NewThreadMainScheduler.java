package in.mvpstarter.sample.util.rx.scheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lam on 2/6/17.
 */

public class NewThreadMainScheduler<T> extends BaseScheduler<T> {

    protected NewThreadMainScheduler() {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }
}
