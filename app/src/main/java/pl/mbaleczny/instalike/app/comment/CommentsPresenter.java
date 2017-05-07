package pl.mbaleczny.instalike.app.comment;

import javax.inject.Inject;

import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.AbstractBasePresenter;

public class CommentsPresenter
        extends AbstractBasePresenter<CommentsContract.View>
        implements CommentsContract.Presenter {

    @Inject
    DataSource repo;

    public CommentsPresenter(DataSource repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void onLoadData(long imageId) {
        view.showProgress();
        disposable.add(repo.getComments(imageId)
                .doAfterTerminate(() -> view.hideProgress())
                .subscribe(c -> view.setCommentsList(c), t -> view.showMessage(t.getMessage())));
    }

    @Override
    public void onPostReceived(Post post) {
        view.setImageUrl(post.getImageUrl());
        view.setCommentText(post.getComment());
        view.setLikesCount(post);
        onLoadData(post.getId());
    }

    @Override
    public void onClick(Post item) {
        view.openLikeListView(item.getId());
    }
}
