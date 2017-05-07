package pl.mbaleczny.instalike.app.news;

import java.util.List;

import pl.mbaleczny.instalike.app.likes.OnClickLikesListener;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.BasePresenter;
import pl.mbaleczny.instalike.util.base.BaseView;

public interface NewsContract {

    interface View extends BaseView {

        void setPosts(List<Post> posts);

        void openLikesListView(long imageId);
    }

    interface Presenter extends BasePresenter<View>, OnClickLikesListener<Post> {

        void onLoadNews(long eventId, long userId);
    }
}
