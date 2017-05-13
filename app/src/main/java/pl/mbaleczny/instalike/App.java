package pl.mbaleczny.instalike;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import pl.mbaleczny.instalike.dagger.app.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
