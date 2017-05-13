package pl.mbaleczny.instalike.dagger.domain;

import dagger.Subcomponent;
import pl.mbaleczny.instalike.dagger.scope.DomainScope;
import pl.mbaleczny.instalike.domain.Repository;

@DomainScope
@Subcomponent(modules = ServiceFactoryModule.class)
public interface DomainComponent {

    Repository repository();

    @Subcomponent.Builder
    interface Builder {

        DomainComponent build();
    }
}
