package io.caster.retrofitting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import io.caster.retrofitting.api.BookService;
import io.caster.retrofitting.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DemoApplication) getApplication()).getAppComponent().inject(this);

        bookService.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                Timber.d("Got some books back!");
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.e(t, "Failed to get books!");
            }
        });

    }
}
