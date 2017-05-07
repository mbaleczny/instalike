package pl.mbaleczny.instalike.app.news;

import java.util.List;

import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.domain.model.User;
import pl.mbaleczny.instalike.util.base.BasePresenter;
import pl.mbaleczny.instalike.util.base.BaseView;

public interface NewsContract {

    interface View extends BaseView {

        void setPosts(List<Post> posts);

        void openLikesListView(List<User> userList);
    }

    interface Presenter extends BasePresenter<View> {

        void onLoadNews(long eventId, long userId);
    }
}
