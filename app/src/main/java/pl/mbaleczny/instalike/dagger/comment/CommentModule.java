package pl.mbaleczny.instalike.dagger.comment;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.app.comment.CommentsContract;
import pl.mbaleczny.instalike.app.comment.CommentsPresenter;
import pl.mbaleczny.instalike.domain.DataSource;

@Module
public class CommentModule {

    @Provides
    CommentsContract.Presenter provideCommentsPresenter(DataSource dataSource) {
        return new CommentsPresenter(dataSource);
    }
}
