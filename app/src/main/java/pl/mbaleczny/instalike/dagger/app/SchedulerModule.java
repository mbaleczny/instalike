package pl.mbaleczny.instalike.dagger.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;
import pl.mbaleczny.instalike.util.scheduler.SchedulerProvider;

@Module
public class SchedulerModule {

    @Singleton
    @Provides
    BaseSchedulerProvider provideScheduler() {
        return new SchedulerProvider();
    }
}
