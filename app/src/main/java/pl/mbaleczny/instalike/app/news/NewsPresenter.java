package pl.mbaleczny.instalike.app.news;

import pl.mbaleczny.instalike.domain.DataSource;
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
                .doOnEvent((n, t) -> view.hideProgress())
                .subscribe(news -> view.setPosts(news.getData()), t -> view.showMessage(t.getMessage())));
    }
}

