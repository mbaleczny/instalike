package pl.mbaleczny.instalike.dagger.news;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.app.news.NewsContract;
import pl.mbaleczny.instalike.app.news.NewsPresenter;
import pl.mbaleczny.instalike.dagger.scope.NewsScope;
import pl.mbaleczny.instalike.domain.DataSource;

@Module
public class NewsModule {

    @NewsScope
    @Provides
    NewsContract.Presenter provideNewsPresenter(DataSource dataSource) {
        return new NewsPresenter(dataSource);
    }
}
