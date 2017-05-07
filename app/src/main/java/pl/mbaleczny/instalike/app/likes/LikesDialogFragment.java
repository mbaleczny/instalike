package pl.mbaleczny.instalike.app.likes;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.domain.model.User;

public class LikesDialogFragment extends DialogFragment {

    public static final String TAG = "LikesDialogFragment";
    private static final String USER_LIST_ARG = "userListArg";

    public static LikesDialogFragment newInstance(List<User> users) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(USER_LIST_ARG, new ArrayList<>(users));
        LikesDialogFragment d = new LikesDialogFragment();
        d.setArguments(args);
        return d;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_likes_list, container);

        RecyclerView likesRecycler = (RecyclerView) v.findViewById(R.id.dialog_likes_list_recycler);
        TextView message = (TextView) v.findViewById(R.id.dialog_likes_list_message);

        if (isUserListNullOrEmpty()) {
            likesRecycler.setVisibility(View.GONE);
        } else {
            message.setVisibility(View.GONE);
            likesRecycler.setHasFixedSize(true);
            likesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            likesRecycler.setAdapter(new LikesRecyclerAdapter(inflater,
                    getArguments().getParcelableArrayList(USER_LIST_ARG)));
        }

        return v;
    }

    private boolean isUserListNullOrEmpty() {
        return getArguments() == null ||
                getArguments().getParcelableArrayList(USER_LIST_ARG) == null ||
                getArguments().getParcelableArrayList(USER_LIST_ARG).isEmpty();
    }

    private class LikesRecyclerAdapter extends RecyclerView.Adapter<LikesRecyclerAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<User> userList;

        LikesRecyclerAdapter(LayoutInflater inflater, List<User> userList) {
            this.inflater = inflater;
            this.userList = userList;
        }

        @Override
        public LikesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.likes_item, parent, false));
        }

        @Override
        public void onBindViewHolder(LikesRecyclerAdapter.ViewHolder holder, int position) {
            holder.bind(userList.get(position));
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(View itemView) {
                super(itemView);
            }

            void bind(User user) {
                TextView item = ((TextView) itemView);
                item.setText(user.getUsername());
                item.setOnClickListener(v -> item.setText(
                        String.format(getContext().getString(R.string.first_and_last_name_pattern),
                                user.getFirstName(), user.getLastName())));
            }
        }
    }
}
