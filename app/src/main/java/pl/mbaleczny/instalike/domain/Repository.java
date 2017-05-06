package pl.mbaleczny.instalike.domain;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import pl.mbaleczny.instalike.domain.api.InstalikeApi;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;

public class Repository implements DataSource {

    private final InstalikeApi api;
    private final BaseSchedulerProvider scheduler;

    public Repository(InstalikeApi api, BaseSchedulerProvider scheduler) {
        this.api = api;
        this.scheduler = scheduler;
    }

    @Override
    public Single<News> getNewsFeed(long eventId, long userId, String token) {
        return api.getFeed(eventId, userId, token)
                .compose(applySchedulers());
    }

    @Override
    public Single<List<Comment>> getComments(long imageId, long userId, String token) {
        return api.getComments(imageId, userId, token)
                .compose(applySchedulers());
    }

    private <T> SingleTransformer<T, T> applySchedulers() {
        return upstream -> upstream
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui());
    }
}
