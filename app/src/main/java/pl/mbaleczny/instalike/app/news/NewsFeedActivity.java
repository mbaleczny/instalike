package pl.mbaleczny.instalike.app.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.mbaleczny.instalike.R;

public class NewsFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_container, NewsFragment.newInstance(), NewsFragment.TAG)
                    .commit();
        }
    }
}
