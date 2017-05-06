package pl.mbaleczny.instalike.util.base;

import android.support.annotation.VisibleForTesting;

import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractBasePresenter<V extends BaseView> implements BasePresenter<V> {

    protected final CompositeDisposable disposable;
    protected V view;

    public AbstractBasePresenter() {
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

    @VisibleForTesting
    public V getView() {
        return view;
    }

    @VisibleForTesting
    public CompositeDisposable getDisposable() {
        return disposable;
    }
}
