package pl.mbaleczny.instalike.dagger.app;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.mbaleczny.instalike.app.comment.CommentsActivity;
import pl.mbaleczny.instalike.app.likes.LikesDialogFragment;
import pl.mbaleczny.instalike.app.news.NewsFeedActivity;
import pl.mbaleczny.instalike.app.news.NewsFragment;

/**
 * @author Mariusz Baleczny
 * @date 13.05.17
 */

@Module
abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract NewsFeedActivity newsFeedActivity();

    @ContributesAndroidInjector
    abstract CommentsActivity commentsActivity();

    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();

    @ContributesAndroidInjector
    abstract LikesDialogFragment likesDialogFragment();
}
