package pl.mbaleczny.instalike.app.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.domain.model.Post;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Post> postList;

    public PostRecyclerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.postList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_vh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(postList.get(position));
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, user, comments, likes;
        private ImageView image;
        private ImageButton like;
        private ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.post_vh_title);
            user = (TextView) itemView.findViewById(R.id.post_vh_user);
            comments = (TextView) itemView.findViewById(R.id.post_vh_comments_counter);
            likes = (TextView) itemView.findViewById(R.id.post_vh_likes_counter);
            image = (ImageView) itemView.findViewById(R.id.post_vh_image);
            like = (ImageButton) itemView.findViewById(R.id.post_vh_like_button);
            progressBar = (ProgressBar) itemView.findViewById(R.id.post_vh_progress_bar);
        }

        void bind(Post post) {
            if (!TextUtils.isEmpty(post.getComment())) {
                title.setVisibility(View.VISIBLE);
                title.setText(post.getComment());
            }

            user.setText(post.getUser().getUsername());
            user.setOnClickListener(v -> {
                user.setText(
                        String.format(context.getString(R.string.first_and_last_name_pattern),
                                post.getUser().getFirstName(), post.getUser().getLastName()));
                        user.setOnClickListener(null);
                    }
            );

            comments.setText(String.valueOf(post.getCommentsCount()));

            likes.setText(String.valueOf(post.getLikesCount()));
            likes.setOnClickListener(v -> Post.LIKE_LIST_BUS.post(post));

            like.setBackgroundResource(post.isUserLiked() ?
                    R.drawable.like_button_sel :
                    R.drawable.like_button_unsel);

            // TODO send to CommentsActivity
            View.OnClickListener onGoToCommentsListener = v ->
                    Toast.makeText(context, "Comments", Toast.LENGTH_SHORT).show();

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
