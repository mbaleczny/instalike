package pl.mbaleczny.instalike.app.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.app.comment.CommentsActivity;
import pl.mbaleczny.instalike.app.likes.OnClickLikesListener;
import pl.mbaleczny.instalike.domain.model.Post;
import pl.mbaleczny.instalike.util.FontUtil;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Post> postList;
    private FontUtil fontUtil;
    private OnClickLikesListener<Post> onClickLikesListener;
    private OnLastVisibleItemPositionListener onLastVisibleItemListener;

    public PostRecyclerAdapter(Context context, OnClickLikesListener<Post> onClickLikesListener,
                               OnLastVisibleItemPositionListener onLastVisibleItemListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.fontUtil = new FontUtil(context);
        this.postList = new ArrayList<>();
        this.onClickLikesListener = onClickLikesListener;
        this.onLastVisibleItemListener = onLastVisibleItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_vh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(postList.get(position));
        if (position == postList.size() - 1) {
            onLastVisibleItemListener.call();
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void swapList(List<Post> posts) {
        // TODO DiffUtil
        this.postList.clear();
        this.postList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addElements(List<Post> posts) {
        // TODO DiffUtil
        this.postList.addAll(posts);
        notifyDataSetChanged();
    }

    public interface OnLastVisibleItemPositionListener {

        void call();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final static int TITLE = 0, USER = 1, COMMENTS = 2, LIKES = 3;

        @BindViews({R.id.post_vh_title,
                R.id.post_vh_user,
                R.id.post_vh_comments_counter,
                R.id.post_vh_likes_counter})
        List<TextView> texts;

        @BindView(R.id.post_vh_image)
        ImageView image;
        @BindView(R.id.post_vh_like_button)
        ImageButton like;
        @BindView(R.id.post_vh_progress_bar)
        ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Post post) {
            if (!TextUtils.isEmpty(post.getComment())) {
                fontUtil.setMonserratLightFont(texts.get(TITLE));
                texts.get(TITLE).setVisibility(View.VISIBLE);
                texts.get(TITLE).setText(post.getComment());
            } else {
                texts.get(TITLE).setVisibility(View.GONE);
            }

            fontUtil.setTrumpGothicEastBoldFont(texts.get(USER));
            texts.get(USER).setText(post.getUser().getUsername());
            texts.get(USER).setOnClickListener(v -> {
                texts.get(USER).setText(
                        String.format(context.getString(R.string.first_and_last_name_pattern),
                                post.getUser().getFirstName(), post.getUser().getLastName()));
                texts.get(USER).setOnClickListener(null);
                    }
            );

            fontUtil.setMonserratLightFont(texts.get(COMMENTS));
            texts.get(COMMENTS).setText(String.valueOf(post.getCommentsCount()));

            fontUtil.setMonserratLightFont(texts.get(LIKES));
            texts.get(LIKES).setText(String.valueOf(post.getLikesCount()));
            texts.get(LIKES).setOnClickListener(v -> onClickLikesListener.onClick(post));

            like.setBackgroundResource(post.isUserLiked() ?
                    R.mipmap.like_button_selected :
                    R.mipmap.like_button_unselected);

            View.OnClickListener onGoToCommentsListener = v -> {
                Intent i = new Intent(v.getContext(), CommentsActivity.class);
                i.putExtra(CommentsActivity.POST_ARG, post);
                v.getContext().startActivity(i);
            };

            itemView.findViewById(R.id.post_vh_bottom).setOnClickListener(onGoToCommentsListener);
            image.setOnClickListener(onGoToCommentsListener);

            Glide.with(context)
                    .load(post.getImageUrl())
                    .listener(new ImageLoadRequestListener(progressBar))
                    .centerCrop()
                    .into(image);
        }
    }
}
