package pl.mbaleczny.instalike.domain.api;

import java.util.List;

import io.reactivex.Single;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.News;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstalikeApi {

    @GET("/feed")
    Single<News> getFeed(@Query("event_id") long eventId,
                         @Query("user_id") long userId,
                         @Query("token") String token);

    @GET("/feed/comments")
    Single<List<Comment>> getComments(@Query("image_id") long imageId,
                                      @Query("user_id") long userId,
                                      @Query("token") String token);
}
