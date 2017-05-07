package pl.mbaleczny.instalike.domain;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import pl.mbaleczny.instalike.BuildConfig;
import pl.mbaleczny.instalike.domain.api.InstalikeApi;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.Like;
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
    public Single<News> getNewsFeed(long eventId, long userId) {
        return api.getFeed(eventId, userId, BuildConfig.DEV_TOKEN)
                .compose(applySchedulers());
    }

    @Override
    public Single<List<Comment>> getComments(long imageId) {
        return api.getComments(imageId, BuildConfig.DEV_TOKEN)
                .compose(applySchedulers());
    }

    @Override
    public Single<List<Like>> getLikes(long imageId) {
        return api.getLikes(imageId, BuildConfig.DEV_TOKEN)
                .compose(applySchedulers());
    }

    private <T> SingleTransformer<T, T> applySchedulers() {
        return upstream -> upstream
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui());
    }
}
