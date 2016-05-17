package io.caster.retrofitting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import io.caster.retrofitting.api.BookService;
import io.caster.retrofitting.models.Book;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject BookService bookService;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DemoApplication) getApplication()).getAppComponent().inject(this);

        subscription =
                bookService.getBooks()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Book>>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Complete.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.e(e, "Error during book retrieval.");
                            }

                            @Override
                            public void onNext(List<Book> books) {
                                Timber.d("Number of books: %s", books.size());
                            }
                        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
