package pl.mbaleczny.instalike.dagger.app;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.mbaleczny.instalike.App;
import pl.mbaleczny.instalike.dagger.comment.CommentModule;
import pl.mbaleczny.instalike.dagger.domain.DomainModule;
import pl.mbaleczny.instalike.dagger.likes.LikesModule;
import pl.mbaleczny.instalike.dagger.news.NewsModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DomainModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                NewsModule.class,
                CommentModule.class,
                LikesModule.class,
                AndroidBindingModule.class,
                AndroidSupportInjectionModule.class
        })
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
