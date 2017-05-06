package pl.mbaleczny.instalike;

import android.app.Application;

import pl.mbaleczny.instalike.dagger.app.AppComponent;
import pl.mbaleczny.instalike.dagger.app.AppModule;
import pl.mbaleczny.instalike.dagger.app.DaggerAppComponent;
import pl.mbaleczny.instalike.dagger.app.NetworkModule;
import pl.mbaleczny.instalike.dagger.domain.DaggerDomainComponent;
import pl.mbaleczny.instalike.dagger.domain.DomainComponent;
import pl.mbaleczny.instalike.dagger.domain.DomainModule;

public class App extends Application {

    private AppComponent appComponent;
    private DomainComponent domainComponent;

    public DomainComponent getDomainComponent() {
        return domainComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        domainComponent = DaggerDomainComponent.builder()
                .appComponent(appComponent)
                .domainModule(new DomainModule())
                .build();
    }
}
