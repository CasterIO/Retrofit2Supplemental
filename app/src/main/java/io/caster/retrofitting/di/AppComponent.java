package io.caster.retrofitting.di;

import javax.inject.Singleton;

import dagger.Component;
import io.caster.retrofitting.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
