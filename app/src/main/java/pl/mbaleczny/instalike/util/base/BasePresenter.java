package pl.mbaleczny.instalike.util.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
