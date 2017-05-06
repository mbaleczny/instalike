package pl.mbaleczny.instalike.dagger.domain;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.dagger.scope.DomainScope;
import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.Repository;
import pl.mbaleczny.instalike.domain.api.InstalikeApi;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;
import retrofit2.Retrofit;

@Module
public class DomainModule {

    @Provides
    @DomainScope
    InstalikeApi provideApi(Retrofit retrofit) {
        return retrofit.create(InstalikeApi.class);
    }

    @Provides
    @DomainScope
    DataSource provideRepository(InstalikeApi api, BaseSchedulerProvider scheduler) {
        return new Repository(api, scheduler);
    }
}
