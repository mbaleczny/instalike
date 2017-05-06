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
        presenter.onLoadNews(1L, 1L);

        Mockito.verify(view).showProgress();
        Mockito.verify(repo).getNewsFeed(Mockito.eq(1L), Mockito.eq(1L));
        Mockito.verify(view).setNewsFeed(Mockito.any(News.class));
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
        Mockito.verify(view, Mockito.never()).setNewsFeed(Mockito.any(News.class));
        Mockito.verify(view).showMessage(Mockito.eq("Error"));
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
