package pl.mbaleczny.instalike.app.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.domain.model.Post;

public class NewsFragment extends Fragment implements NewsContract.View {

    public static final String TAG = "NewsFragment";
    private static final String EVENT_ID = "eventId";
    private static final String USER_ID = "userId";

    @Inject
    NewsContract.Presenter presenter;

    private Lazy<Long> eventId = () -> getArguments().getLong(EVENT_ID);
    private Lazy<Long> userId = () -> getArguments().getLong(USER_ID);

    private RecyclerView postRecycler;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NewsFeedActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        postRecycler = (RecyclerView) v.findViewById(R.id.fragment_post_recycler);
        setupNewsRecycler();
        return v;
    }

    private void setupNewsRecycler() {
        postRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostRecyclerAdapter(getContext());
        postRecycler.setAdapter(postAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        presenter.onLoadNews(eventId.get(), userId.get());
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
        // TODO: 06.05.17
        postAdapter.swapList(posts);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        // TODO: 06.05.17
    }

    @Override
    public void hideProgress() {
        // TODO: 06.05.17
    }
}
