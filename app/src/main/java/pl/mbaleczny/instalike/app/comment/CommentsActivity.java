package pl.mbaleczny.instalike.app.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.app.likes.LikesDialogFragment;
import pl.mbaleczny.instalike.domain.model.Comment;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.FontUtil;

public class CommentsActivity extends DaggerAppCompatActivity implements CommentsContract.View {

    public static final String POST_ARG = "postArg";

    @BindView(R.id.activity_comment_toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.activity_comment_text)
    TextView commentText;
    @BindView(R.id.activity_comment_likes_counter)
    TextView likesCounter;
    @BindView(R.id.activity_comment_empty_list_label)
    TextView emptyListLabel;
    @BindView(R.id.activity_comment_input_text)
    EditText inputText;
    @BindView(R.id.activity_comment_image)
    ImageView image;

    @BindView(R.id.activity_comment_empty_list_layout)
    RelativeLayout emptyListLayout;
    @BindView(R.id.activity_comment_text_layout)
    RelativeLayout commentTextLayout;

    @BindView(R.id.activity_comment_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.activity_comment_list_recycler)
    RecyclerView commentsRecycler;

    @BindView(R.id.activity_comment_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_comment_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindString(R.string.comment_text_pattern)
    String commentTextPattern;

    @Inject
    CommentsContract.Presenter presenter;

    @Inject
    FontUtil fontUtil;

    private CommentsRecyclerAdapter commentsAdapter;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        setupToolbars();
        setupCommentsRecycler();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            commentsAdapter.swapList(new ArrayList<>());
            presenter.onLoadData(post.getId());
        });

        fontUtil.setTrumpGothicEastBoldFont(toolbarTitle);
        fontUtil.setMonserratLightFont(commentText);
        fontUtil.setMonserratLightFont(likesCounter);
        fontUtil.setMonserratLightFont(emptyListLabel);
        fontUtil.setMonserratLightFont(inputText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (getIntent() != null && getIntent().hasExtra(POST_ARG)) {
            post = getIntent().getParcelableExtra(POST_ARG);
            presenter.onPostReceived(post);
        } else {
            Toast.makeText(this, R.string.internal_app_error_message, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupToolbars() {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setupCommentsRecycler() {
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentsAdapter = new CommentsRecyclerAdapter(this);
        commentsRecycler.setAdapter(commentsAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCommentsList(List<Comment> comments) {
        emptyListLayout.setVisibility(comments.isEmpty() ? View.VISIBLE : View.GONE);
        commentsAdapter.swapList(comments);
    }

    @Override
    public void setImageUrl(String imageUrl) {
        Glide.with(this).load(imageUrl).centerCrop().into(image);
    }

    @Override
    public void setCommentText(String commentText) {
        if (TextUtils.isEmpty(commentText)) {
            commentTextLayout.setVisibility(View.GONE);
        } else {
            this.commentText.setText(String.format(commentTextPattern, commentText));
        }
    }

    @Override
    public void setLikesCount(Post post) {
        likesCounter.setText(String.valueOf(post.getLikesCount()));
        likesCounter.setOnClickListener(v -> presenter.onClick(post));
    }

    @Override
    public void openLikeListView(long imageId) {
        LikesDialogFragment.newInstance(imageId).show(getSupportFragmentManager(), LikesDialogFragment.TAG);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
