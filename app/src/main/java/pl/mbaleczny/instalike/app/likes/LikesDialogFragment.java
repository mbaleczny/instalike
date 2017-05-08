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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.mbaleczny.instalike.App;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.dagger.likes.DaggerLikesComponent;
import pl.mbaleczny.instalike.dagger.likes.LikesModule;
import pl.mbaleczny.instalike.domain.model.User;
import pl.mbaleczny.instalike.util.FontUtil;

public class LikesDialogFragment extends DialogFragment implements LikesContract.View {

    public static final String TAG = "LikesDialogFragment";

    private static final String IMAGE_ID_ARG = "imageIdArg";

    @BindView(R.id.dialog_likes_list_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.dialog_likes_list_recycler)
    RecyclerView userRecycler;
    @BindView(R.id.dialog_likes_list_message)
    TextView message;
    @BindView(R.id.dialog_likes_list_title)
    TextView listTitle;

    @Inject
    LikesContract.Presenter presenter;
    @Inject
    FontUtil fontUtil;

    private LikesRecyclerAdapter likesAdapter;

    public static LikesDialogFragment newInstance(long imageId) {
        Bundle args = new Bundle();
        args.putLong(IMAGE_ID_ARG, imageId);
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

        injectDependencies();

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_likes_list, container);
        ButterKnife.bind(this, v);

        userRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        likesAdapter = new LikesRecyclerAdapter(inflater);
        userRecycler.setAdapter(likesAdapter);

        fontUtil.setMonserratLightFont(listTitle);
        fontUtil.setMonserratLightFont(message);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        if (hasImageIdArgument()) {
            presenter.onLoadLikes(getArguments().getLong(IMAGE_ID_ARG));
        }
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        // Already shown
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserList(List<User> userList) {
        if (userList.isEmpty()) {
            message.setVisibility(View.VISIBLE);
        }
        likesAdapter.setUserList(userList);
    }

    private boolean hasImageIdArgument() {
        return getArguments() == null || getArguments().containsKey(IMAGE_ID_ARG);
    }

    private void injectDependencies() {
        DaggerLikesComponent.builder()
                .domainComponent(((App) getActivity().getApplication()).getDomainComponent())
                .likesModule(new LikesModule())
                .build().inject(this);
    }

    private class LikesRecyclerAdapter extends RecyclerView.Adapter<LikesRecyclerAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<User> userList;

        LikesRecyclerAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
            userList = new ArrayList<>();
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

        void setUserList(List<User> userList) {
            this.userList.addAll(userList);
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(View itemView) {
                super(itemView);
                fontUtil.setMonserratLightFont((TextView) itemView);
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
