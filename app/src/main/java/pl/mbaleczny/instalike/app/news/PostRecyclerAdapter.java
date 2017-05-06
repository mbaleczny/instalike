package pl.mbaleczny.instalike.app.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.mbaleczny.instalike.domain.model.Post;

/**
 * @author Mariusz Baleczny
 * @date 06.05.17
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final List<Post> postList;

    public PostRecyclerAdapter(Context context) {
        this.context = context;
        this.postList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void swapList(List<Post> posts) {
        // TODO DiffUtil
        this.postList.clear();
        this.postList.addAll(posts);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Post post) {
            // TODO
        }
    }
}
