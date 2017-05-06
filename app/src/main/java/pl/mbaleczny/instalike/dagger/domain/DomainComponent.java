package pl.mbaleczny.instalike.dagger.domain;

import dagger.Component;
import pl.mbaleczny.instalike.dagger.app.AppComponent;
import pl.mbaleczny.instalike.dagger.scope.DomainScope;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;

@DomainScope
@Component(dependencies = AppComponent.class, modules = DomainModule.class)
public interface DomainComponent {

    BaseSchedulerProvider scheduler();
}
