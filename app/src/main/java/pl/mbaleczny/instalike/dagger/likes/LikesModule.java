package pl.mbaleczny.instalike.dagger.likes;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.app.likes.LikesContract;
import pl.mbaleczny.instalike.app.likes.LikesPresenter;
import pl.mbaleczny.instalike.domain.DataSource;

@Module
public class LikesModule {

    @Provides
    LikesContract.Presenter provideLikesPresenter(DataSource dataSource) {
        return new LikesPresenter(dataSource);
    }
}
