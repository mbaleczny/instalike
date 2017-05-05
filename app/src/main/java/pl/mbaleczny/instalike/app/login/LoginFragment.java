package pl.mbaleczny.instalike.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.app.news.NewsFeedActivity;

public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment";

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById(R.id.fragment_login_button)
                .setOnClickListener(v -> goToNewsFeedActivity());

        return view;
    }

    private void goToNewsFeedActivity() {
        Intent i = new Intent(getContext(), NewsFeedActivity.class);
        getActivity().startActivity(i);
    }
}
