package pl.mbaleczny.instalike.app.news;

import java.util.List;

import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.AbstractBasePresenter;

public class NewsPresenter
        extends AbstractBasePresenter<NewsContract.View>
        implements NewsContract.Presenter {

    private static final int FIRST_PAGE = 1;

    private final DataSource repo;
    private News currentNews;

    private long eventId;
    private long userId;

    public NewsPresenter(DataSource repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void onLoadFirstPage() {
        view.showProgress();
        disposable.add(repo.getNewsFeed(eventId, userId, FIRST_PAGE)
                .map(this::getNewsAndMapToPosts)
                .doAfterTerminate(() -> view.hideProgress())
                .subscribe(posts -> view.setPosts(posts), this::onError));
    }

    @Override
    public void onLoadNews(int page) {
        view.showProgress();
        disposable.add(repo.getNewsFeed(eventId, userId, page)
                .map(this::getNewsAndMapToPosts)
                .doAfterTerminate(() -> view.hideProgress())
                .subscribe(posts -> view.addPosts(posts), this::onError));
    }

    @Override
    public void setNewsIds(long eventId, long userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    @Override
    public void onClick(Post item) {
        view.openLikesListView(item.getId());
    }

    @Override
    public void call() {
        if (currentNews.getCurrentPage() < currentNews.getLastPage()) {
            onLoadNews(currentNews.getCurrentPage() + 1);
        }
    }

    private List<Post> getNewsAndMapToPosts(News news) {
        currentNews = news;
        return news.getData();
    }

    private void onError(Throwable t) {
        view.showMessage(t.getMessage());
    }
}

