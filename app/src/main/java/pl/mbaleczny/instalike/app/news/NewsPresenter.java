package pl.mbaleczny.instalike.app.news;

import android.support.annotation.VisibleForTesting;

import io.reactivex.functions.Consumer;
import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.Like;
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.AbstractBasePresenter;

public class NewsPresenter
        extends AbstractBasePresenter<NewsContract.View>
        implements NewsContract.Presenter {

    private final DataSource repo;
    private Consumer<Post> onLikeListConsumer;

    public NewsPresenter(DataSource repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void onLoadNews(long eventId, long userId) {
        view.showProgress();
        disposable.add(repo.getNewsFeed(eventId, userId)
                .map(News::getData)
                .doOnEvent((n, t) -> view.hideProgress())
                .subscribe(posts -> view.setPosts(posts), t -> view.showMessage(t.getMessage())));
    }

    @Override
    public void attachView(NewsContract.View view) {
        super.attachView(view);
        if (onLikeListConsumer == null) {
            onLikeListConsumer = it -> onLoadLikes(it.getId());
            Post.LIKE_LIST_BUS.subscribe(onLikeListConsumer);
        }
    }

    @VisibleForTesting
    public void onLoadLikes(long id) {
        view.showProgress();

        disposable.add(repo.getLikes(id)
                .toObservable()
                .flatMapIterable(it -> it)
                .map(Like::getUser)
                .toList()
                .doOnEvent((n, t) -> view.hideProgress())
                .subscribe(u -> view.openLikesListView(u), t -> view.showMessage(t.getMessage())));
    }

    @Override
    public void detachView() {
        super.detachView();
        Post.LIKE_LIST_BUS.clear();
        onLikeListConsumer = null;
    }
}

