package pl.mbaleczny.instalike.app.likes;

import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.Like;
import pl.mbaleczny.instalike.util.base.AbstractBasePresenter;

public class LikesPresenter
        extends AbstractBasePresenter<LikesContract.View>
        implements LikesContract.Presenter {

    private DataSource repo;

    public LikesPresenter(DataSource repo) {
        this.repo = repo;
    }

    @Override
    public void onLoadLikes(long id) {
        view.showProgress();
        disposable.add(repo.getLikes(id)
                .toObservable()
                .flatMapIterable(it -> it)
                .map(Like::getUser)
                .toList()
                .doOnEvent((n, t) -> view.hideProgress())
                .subscribe(u -> view.setUserList(u), t -> view.showMessage(t.getMessage())));
    }
}
