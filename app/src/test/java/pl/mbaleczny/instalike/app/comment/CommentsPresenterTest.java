package pl.mbaleczny.instalike.app.comment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;
import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.LikesCount;
import pl.mbaleczny.instalike.domain.model.Post;

public class CommentsPresenterTest {

    @Mock
    DataSource repo;
    @Mock
    CommentsContract.View view;

    private CommentsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new CommentsPresenter(repo);
    }

    @Test
    public void onLoadData_isSuccessful() throws Exception {
        presenter.attachView(view);

        Mockito.when(repo.getComments(Mockito.anyLong()))
                .thenReturn(Single.just(Collections.singletonList(new Comment())));

        presenter.onLoadData(1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getComments(Mockito.eq(1L));
        Mockito.verify(view).setCommentsList(Mockito.anyListOf(Comment.class));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onLoadData_throwsException() throws Exception {
        presenter.attachView(view);

        Mockito.when(repo.getComments(Mockito.anyLong()))
                .thenReturn(Single.error(new RuntimeException("error")));

        presenter.onLoadData(1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getComments(Mockito.eq(1L));
        Mockito.verify(view).showMessage(Mockito.eq("error"));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onPostReceived_isSuccessful() throws Exception {
        presenter.attachView(view);

        Mockito.when(repo.getComments(Mockito.anyLong()))
                .thenReturn(Single.just(Collections.singletonList(new Comment())));

        Post post = new Post();
        post.setImageUrl("test_url");
        post.setComment("test");
        post.setId(1L);
        post.setLikes(Collections.singletonList(new LikesCount()));

        presenter.onPostReceived(post);

        Mockito.verify(view).showProgress();
        Mockito.verify(view).setImageUrl(Mockito.eq("test_url"));
        Mockito.verify(view).setCommentText(Mockito.eq("test"));
        Mockito.verify(view).setLikesCount(Mockito.eq(post));
        Mockito.verify(repo).getComments(Mockito.eq(1L));
        Mockito.verify(view).setCommentsList(Mockito.anyListOf(Comment.class));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onPostReceived_throwsException() throws Exception {
        presenter.attachView(view);

        Mockito.when(repo.getComments(Mockito.anyLong()))
                .thenReturn(Single.error(new RuntimeException("error")));

        Post post = new Post();
        post.setImageUrl("test_url");
        post.setComment("test");
        post.setId(1L);
        post.setLikes(null);

        presenter.onPostReceived(post);

        Mockito.verify(view).showProgress();
        Mockito.verify(view).setImageUrl(Mockito.eq("test_url"));
        Mockito.verify(view).setCommentText(Mockito.eq("test"));
        Mockito.verify(view).setLikesCount(Mockito.eq(post));
        Mockito.verify(repo).getComments(Mockito.eq(1L));
        Mockito.verify(view).showMessage(Mockito.eq("error"));
        Mockito.verify(view).hideProgress();
    }
}