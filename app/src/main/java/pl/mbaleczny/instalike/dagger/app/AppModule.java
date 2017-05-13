package pl.mbaleczny.instalike.dagger.app;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.App;

@Module
abstract class AppModule {

    @Provides
    static Context provideContext(App app) {
        return app;
    }

    @Binds
    abstract Application application(App app);
}
