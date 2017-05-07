package pl.mbaleczny.instalike.dagger.comment;

import dagger.Component;
import pl.mbaleczny.instalike.app.comment.CommentsActivity;
import pl.mbaleczny.instalike.dagger.domain.DomainComponent;
import pl.mbaleczny.instalike.dagger.scope.CommentScope;

@CommentScope
@Component(dependencies = DomainComponent.class, modules = CommentModule.class)
public interface CommentComponent {

    void inject(CommentsActivity activity);
}
