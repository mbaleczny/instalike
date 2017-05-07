package pl.mbaleczny.instalike.dagger.likes;

import dagger.Component;
import pl.mbaleczny.instalike.app.likes.LikesDialogFragment;
import pl.mbaleczny.instalike.dagger.domain.DomainComponent;
import pl.mbaleczny.instalike.dagger.scope.LikesScope;

@LikesScope
@Component(dependencies = DomainComponent.class, modules = LikesModule.class)
public interface LikesComponent {

    void inject(LikesDialogFragment fragment);
}
