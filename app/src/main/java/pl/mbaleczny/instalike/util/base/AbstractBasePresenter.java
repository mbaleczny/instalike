package pl.mbaleczny.instalike.util.base;

import io.reactivex.disposables.CompositeDisposable;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;

public abstract class AbstractBasePresenter<V extends BaseView> implements BasePresenter<V> {

    protected final CompositeDisposable disposable;
    protected final BaseSchedulerProvider scheduler;
    protected V view;

    public AbstractBasePresenter(BaseSchedulerProvider scheduler) {
        this.scheduler = scheduler;
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposable.clear();
        view = null;
    }
}
