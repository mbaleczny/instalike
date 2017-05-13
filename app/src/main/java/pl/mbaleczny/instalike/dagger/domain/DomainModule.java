package pl.mbaleczny.instalike.dagger.domain;

import dagger.Module;
import dagger.Provides;
import pl.mbaleczny.instalike.domain.DataSource;

@Module(subcomponents = DomainComponent.class)
public class DomainModule {

    @Provides
    DataSource provideRepository(DomainComponent.Builder builder) {
        return builder.build().repository();
    }
}
