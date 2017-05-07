package pl.mbaleczny.instalike.app.news;

import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.AbstractBasePresenter;

public class NewsPresenter
        extends AbstractBasePresenter<NewsContract.View>
        implements NewsContract.Presenter {

    private final DataSource repo;

    public NewsPresenter(DataSource repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void onLoadNews(long eventId, long userId) {
        view.showProgress();
        disposable.add(repo.getNewsFeed(eventId, userId)
                .map(News::getData)
                .doAfterTerminate(() -> view.hideProgress())
                .subscribe(posts -> view.setPosts(posts), t -> view.showMessage(t.getMessage())));
    }

    @Override
    public void onClick(Post item) {
        view.openLikesListView(item.getId());
    }
}

