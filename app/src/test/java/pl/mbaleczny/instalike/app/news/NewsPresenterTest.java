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
import pl.mbaleczny.instalike.domain.model.News;
import pl.mbaleczny.instalike.domain.model.Post;

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
        presenter.setNewsIds(1L, 1L);
        presenter.onLoadFirstPage();

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getNewsFeed(Mockito.eq(1L), Mockito.eq(1L), Mockito.eq(1));
        Mockito.verify(view).setPosts(Mockito.anyListOf(Post.class));
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void onLoadNews_throwsException() throws Exception {
        Mockito.when(repo.getNewsFeed(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt()))
                .thenReturn(Single.error(new RuntimeException("Error")));

        presenter.attachView(view);
        presenter.setNewsIds(1L, 1L);
        presenter.onLoadNews(0);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getNewsFeed(Mockito.eq(1L), Mockito.eq(1L), Mockito.eq(0));
        Mockito.verify(view, Mockito.never()).setPosts(Mockito.anyListOf(Post.class));
        Mockito.verify(view).showMessage(Mockito.eq("Error"));
        Mockito.verify(view).hideProgress();
    }

    private void mockRepo() {
        News news = new News();
        news.setTotal(20);
        news.setCurrentPage(1);
        news.setData(Collections.singletonList(new Post()));

        Mockito.when(repo.getNewsFeed(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt()))
                .thenReturn(Single.just(news));
    }
}
