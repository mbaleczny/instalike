package pl.mbaleczny.instalike.dagger.news;

import dagger.Component;
import pl.mbaleczny.instalike.app.news.NewsFragment;
import pl.mbaleczny.instalike.dagger.domain.DomainComponent;
import pl.mbaleczny.instalike.dagger.scope.NewsScope;

@NewsScope
@Component(dependencies = DomainComponent.class, modules = NewsModule.class)
public interface NewsComponent {

    void inject(NewsFragment fragment);
}
