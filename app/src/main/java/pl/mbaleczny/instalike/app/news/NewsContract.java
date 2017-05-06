package pl.mbaleczny.instalike.app.news;

import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.util.base.BasePresenter;
import pl.mbaleczny.instalike.util.base.BaseView;

public interface NewsContract {

    interface View extends BaseView {

        void setNewsFeed(News news);
    }

    interface Presenter extends BasePresenter<View> {

        void onLoadNews(long eventId, long userId);
    }
}
