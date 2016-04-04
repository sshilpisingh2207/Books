package com.example.shilpisingh.books;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by shilpisingh on 3/25/16.
 */
public final class SimpleService {
    public static final String API_URL = "http://prolific-interview.herokuapp.com/56eb7083cada930009ab09a0/";
    public static class Book {
        public int id;
        public String author;
        public String categories;
        public String title;
        public String publisher;
        public String lastCheckedOutBy;
        public String lastCheckedOut;


        public Book(String author, String categories, String title, String publisher,String lastcheckedoutby,String lastcheckeddate) {
            this.author = author;
            this.categories = categories;
            this.title = title;
            this.publisher = publisher;
            this.lastCheckedOutBy=lastcheckedoutby;
            this.lastCheckedOut=lastcheckeddate;
        }
    }

    public interface BookAPI {
        @GET("books/")
        Call<List<Book>> getBooks();

        @GET("books/{id}/")
        Call<Book> getBook(@Path("id") int bookId);

        @POST("books/")
        Call<ResponseBody> createBook(@Body Book book);

        @PUT("books/{id}/")
        Call<Book> updateBook(@Path("id") int bookId, @Body Book book);

        @DELETE("books/{id}/")
        Call<ResponseBody> deleteBook(@Path("id") int bookId);

        @DELETE("clean/")
        Call<ResponseBody> clean();
    }

    public static void getBooks(final Callback cbvalue) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        final BookAPI bookapi = retrofit.create(BookAPI.class);
        final Call<List<Book>> call = bookapi.getBooks();

        call.enqueue(new Callback<List<Book>>() {


            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response1) {
                cbvalue.onResponse(call,response1);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }

    public static void get(int ik, final Callback bvalue ) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        BookAPI bookapi = retrofit.create(BookAPI.class);
        Call<Book> call = bookapi.getBook(ik);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                final Book b = new Book("","","","","","");

                b.author =response.body().author;
                b.title=response.body().title;
                b.publisher=response.body().publisher;
                b.categories=response.body().categories;
                b.lastCheckedOutBy=response.body().lastCheckedOutBy;
                b.lastCheckedOut=response.body().lastCheckedOut;
                bvalue.onResponse(call, response);

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }

    public static void post(String author,String categories ,String title, String publisher ) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();


        BookAPI bookapi = retrofit.create(BookAPI.class);

        Book book = new Book(author, categories, title, publisher,null,null);

        Call<ResponseBody> call = bookapi.createBook(book);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public static void put(int id,String checkedby,String date){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        BookAPI bookapi = retrofit.create(BookAPI.class);
        System.out.println("outsise put lastcheckedby"+checkedby);
        System.out.println("outside put lastcheckeddate"+date);

        Book book7=new Book(null,null,null,null,checkedby,date);
        Call<Book> call = bookapi.updateBook(id, book7);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
            }

        });
    }


    public static void clean() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        System.out.println("I came here");

        BookAPI bookapi = retrofit.create(BookAPI.class);
        Call<ResponseBody> call = bookapi.clean();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    public static void delete(int id) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        BookAPI bookapi = retrofit.create(BookAPI.class);

        Call<ResponseBody> call = bookapi.deleteBook(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
