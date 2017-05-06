package pl.mbaleczny.instalike.domain;

import java.util.List;

import io.reactivex.Single;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.Feed;

public interface DataSource {

    Single<Feed> getFeed(long eventId, long userId, String token);

    Single<List<Comment>> getComments(long imageId, long userId, String token);
}
