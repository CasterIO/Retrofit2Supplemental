package io.caster.retrofitting.api;


import java.util.List;

import io.caster.retrofitting.models.Book;
import retrofit2.http.GET;
import rx.Observable;

public interface BookService {

    @GET("books")
    Observable<List<Book>> getBooks();
}
