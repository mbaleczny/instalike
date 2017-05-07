package pl.mbaleczny.instalike.app.news;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;
import pl.mbaleczny.instalike.domain.DataSource;
import pl.mbaleczny.instalike.domain.model.Like;
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.domain.model.User;

public class NewsPresenterTest {

    @Mock
    NewsContract.View view;
    @Mock
    DataSource repo;

    private NewsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockRepo();
        presenter = new NewsPresenter(repo);
    }

    @Test
    public void attachView() throws Exception {
        presenter.attachView(view);

        Assert.assertEquals(view, presenter.getView());
    }

    @Test
    public void detachView() throws Exception {
        presenter.attachView(view);

        presenter.getDisposable().add(Single.just(1).subscribe());
        presenter.detachView();

        Assert.assertNull(presenter.getView());
        Assert.assertEquals(0, presenter.getDisposable().size());
    }

    @Test
    public void onLoadNews_isSuccessful() throws Exception {
        presenter.attachView(view);
        presenter.onLoadNews(1L, 1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getNewsFeed(Mockito.eq(1L), Mockito.eq(1L));
        Mockito.verify(view).setPosts(Mockito.anyListOf(Post.class));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onLoadNews_throwsException() throws Exception {
        Mockito.when(repo.getNewsFeed(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Single.error(new RuntimeException("Error")));

        presenter.attachView(view);
        presenter.onLoadNews(1L, 1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getNewsFeed(Mockito.eq(1L), Mockito.eq(1L));
        Mockito.verify(view, Mockito.never()).setPosts(Mockito.anyListOf(Post.class));
        Mockito.verify(view).showMessage(Mockito.eq("Error"));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onLoadLikes_isSuccessful() throws Exception {
        User u = new User();
        u.setUsername("tester");

        Like like = new Like();
        like.setUser(u);

        Mockito.when(repo.getLikes(Mockito.eq(1L)))
                .thenReturn(Single.just(Collections.singletonList(like)));

        presenter.attachView(view);
        presenter.onLoadLikes(1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getLikes(Mockito.eq(1L));
        Mockito.verify(view).openLikesListView(Mockito.anyListOf(User.class));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onLoadLikes_throwsException() throws Exception {
        User u = new User();
        u.setUsername("tester");

        Like like = new Like();
        like.setUser(u);

        Mockito.when(repo.getLikes(Mockito.eq(1L)))
                .thenReturn(Single.error(new RuntimeException("error")));

        presenter.attachView(view);
        presenter.onLoadLikes(1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getLikes(Mockito.eq(1L));
        Mockito.verify(view, Mockito.never()).openLikesListView(Mockito.anyListOf(User.class));
        Mockito.verify(view).showMessage(Mockito.eq("error"));
        Mockito.verify(view).hideProgress();
    }

    private void mockRepo() {
        News news = new News();
        news.setTotal(20);
        news.setCurrentPage(1);
        news.setData(Collections.singletonList(new Post()));

        Mockito.when(repo.getNewsFeed(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Single.just(news));
    }
}
