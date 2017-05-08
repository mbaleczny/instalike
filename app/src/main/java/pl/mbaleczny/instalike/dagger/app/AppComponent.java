package pl.mbaleczny.instalike.dagger.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.mbaleczny.instalike.util.FontUtil;
import pl.mbaleczny.instalike.util.scheduler.BaseSchedulerProvider;
import retrofit2.Retrofit;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                SchedulerModule.class})
public interface AppComponent {

    BaseSchedulerProvider scheduler();

    Retrofit retrofit();

    Context appContext();

    FontUtil fontUtil();
}
