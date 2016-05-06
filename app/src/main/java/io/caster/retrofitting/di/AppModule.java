package io.caster.retrofitting.di;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import io.caster.retrofitting.Constants;
import io.caster.retrofitting.api.BookService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

//    @Provides @Singleton
//    public Gson provideGson() {
//        // Do fancy stuff with GSON here. :) (Custom date converters/etc)
//        return new GsonBuilder().create();
//    }

    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public BookService provideBookService(Retrofit retrofit) {
        return retrofit.create(BookService.class);
    }
}
