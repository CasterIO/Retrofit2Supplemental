package io.caster.retrofitting;

import android.app.Application;

import io.caster.retrofitting.di.AppComponent;
import io.caster.retrofitting.di.AppModule;
import io.caster.retrofitting.di.DaggerAppComponent;
import timber.log.Timber;

public class DemoApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Logging
        Timber.plant(new Timber.DebugTree());

        // Set up Dagger
        createAppComponent();
    }

    private void createAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
