package pl.mbaleczny.instalike.app.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.domain.model.Comment;

public class CommentsRecyclerAdapter
        extends RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Comment> commentList;

    public CommentsRecyclerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        commentList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.comment_vh, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void swapList(List<Comment> comments) {
        // TODO DiffUtil
        commentList.clear();
        commentList.addAll(comments);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView user, content;

        ViewHolder(View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.comment_vh_user);
            content = (TextView) itemView.findViewById(R.id.comment_vh_content);
        }

        void bind(Comment comment) {
            user.setText(comment.getUser().getUsername());
            content.setText(comment.getComment());
        }
    }
}
