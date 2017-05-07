package pl.mbaleczny.instalike.app.likes;

import java.util.List;

import pl.mbaleczny.instalike.domain.model.User;
import pl.mbaleczny.instalike.util.base.BasePresenter;
import pl.mbaleczny.instalike.util.base.BaseView;

public interface LikesContract {

    interface View extends BaseView {

        void setUserList(List<User> userList);
    }

    interface Presenter extends BasePresenter<View> {

        void onLoadLikes(long id);
    }
}
