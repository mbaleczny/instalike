package pl.mbaleczny.instalike.app.news;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import pl.mbaleczny.instalike.R;

public class NewsFeedActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_container, NewsFragment.newInstance(3L, 3L), NewsFragment.TAG)
                    .commit();
        }
    }
}
