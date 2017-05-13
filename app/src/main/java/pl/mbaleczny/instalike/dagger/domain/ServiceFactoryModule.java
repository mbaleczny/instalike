package pl.mbaleczny.instalike.dagger.domain;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.dagger.scope.DomainScope;
import pl.mbaleczny.instalike.domain.api.InstalikeApi;
import retrofit2.Retrofit;

/**
 * @author Mariusz Baleczny
 * @date 13.05.17
 */

@Module
public class ServiceFactoryModule {

    @Provides
    @DomainScope
    InstalikeApi provideApi(Retrofit retrofit) {
        return retrofit.create(InstalikeApi.class);
    }
}
