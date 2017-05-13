package pl.mbaleczny.instalike.app.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerFragment;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.app.likes.LikesDialogFragment;
import pl.mbaleczny.instalike.domain.model.Post;

public class NewsFragment extends DaggerFragment implements NewsContract.View {

    public static final String TAG = "NewsFragment";
    private static final String EVENT_ID = "eventId";
    private static final String USER_ID = "userId";

    @BindView(R.id.fragment_post_recycler)
    RecyclerView postRecycler;
    @BindView(R.id.fragment_news_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefresh;

    @Inject
    NewsContract.Presenter presenter;

    private Lazy<Long> eventId = () -> getArguments().getLong(EVENT_ID);
    private Lazy<Long> userId = () -> getArguments().getLong(USER_ID);

    private PostRecyclerAdapter postAdapter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance(long eventId, long userId) {
        Bundle args = new Bundle();
        args.putLong(EVENT_ID, eventId);
        args.putLong(USER_ID, userId);
        NewsFragment f = new NewsFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    private void setupNewsRecycler() {
        postRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostRecyclerAdapter(getContext(), presenter, presenter);
        postRecycler.setAdapter(postAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupNewsRecycler();
        setupSwipeRefreshLayout();
        presenter.attachView(this);
        presenter.setNewsIds(eventId.get(), userId.get());
        presenter.onLoadFirstPage();
    }

    private void setupSwipeRefreshLayout() {
        swipeRefresh.setOnRefreshListener(() -> presenter.onLoadFirstPage());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void setPosts(List<Post> posts) {
        postAdapter.swapList(posts);
    }

    @Override
    public void addPosts(List<Post> posts) {
        postAdapter.addElements(posts);
    }

    @Override
    public void openLikesListView(long userList) {
        LikesDialogFragment.newInstance(userList).show(getFragmentManager(), LikesDialogFragment.TAG);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }
}
