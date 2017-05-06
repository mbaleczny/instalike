package pl.mbaleczny.instalike.domain;

import java.util.List;

import io.reactivex.Single;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.News;

public interface DataSource {

    Single<News> getNewsFeed(long eventId, long userId);

    Single<List<Comment>> getComments(long imageId, long userId);
}
