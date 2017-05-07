package pl.mbaleczny.instalike.app.comment;

import java.util.List;

import pl.mbaleczny.instalike.app.likes.OnClickLikesListener;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.base.BasePresenter;
import pl.mbaleczny.instalike.util.base.BaseView;

public interface CommentsContract {

    interface View extends BaseView {

        void setCommentsList(List<Comment> comments);

        void setImageUrl(String imageUrl);

        void setCommentText(String commentText);

        void setLikesCount(Post likesCount);

        void openLikeListView(long imageId);
    }

    interface Presenter extends BasePresenter<View>, OnClickLikesListener<Post> {

        void onLoadData(long imageId);

        void onPostReceived(Post post);
    }
}
