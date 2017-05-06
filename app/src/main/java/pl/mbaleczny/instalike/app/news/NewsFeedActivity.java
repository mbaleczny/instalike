package pl.mbaleczny.instalike.app.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.mbaleczny.instalike.App;
import pl.mbaleczny.instalike.R;
import pl.mbaleczny.instalike.dagger.news.DaggerNewsComponent;
import pl.mbaleczny.instalike.dagger.news.NewsComponent;
import pl.mbaleczny.instalike.dagger.news.NewsModule;

public class NewsFeedActivity extends AppCompatActivity {

    private NewsComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_container, NewsFragment.newInstance(3L, 2L), NewsFragment.TAG)
                    .commit();
        }
    }

    public NewsComponent getComponent() {
        if (component == null) {
            component = DaggerNewsComponent.builder()
                    .domainComponent(((App) getApplication()).getDomainComponent())
                    .newsModule(new NewsModule())
                    .build();
        }
        return component;
    }
}
