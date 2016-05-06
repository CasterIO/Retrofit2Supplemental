package io.caster.retrofitting.api;


import java.util.List;

import io.caster.retrofitting.models.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {

    @GET("books")
    Call<List<Book>> getBooks();
}
