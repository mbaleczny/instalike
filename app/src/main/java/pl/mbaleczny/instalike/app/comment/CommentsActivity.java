package pl.mbaleczny.instalike.app.comment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import pl.mbaleczny.instalike.R;

public class CommentsActivity extends AppCompatActivity {

    private TextView commentText, toolbarTitle, likesCounter;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_comment_toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.activity_comment_collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);

        commentText = (TextView) findViewById(R.id.activity_comment_text);
        toolbarTitle = (TextView) findViewById(R.id.activity_comment_toolbar_title);
        likesCounter = (TextView) findViewById(R.id.activity_comment_likes_counter);
        image = (ImageView) findViewById(R.id.activity_comment_image);
    }
}
