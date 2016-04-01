package com.example.shilpisingh.books;

import android.content.res.TypedArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.TypeInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

    private static String yourOutputStream= "";

    public static class Book {
        public final String author;
        public final String categories;
        public final String title;
        public final String publisher;

        public Book(String author, String categories, String title, String publisher) {
            this.author = author;
            this.categories = categories;
            this.title = title;
            this.publisher = publisher;
        }
    }

    public interface BookAPI {
        @GET("books/")
        Call<ResponseBody> getBooks();

        @GET("books/{id}/")
        Call<ResponseBody> getBook(@Path("id") int bookId);

        @POST("books/")
        Call<ResponseBody> createBook(@Body Book book);

        @PUT("books/{id}/")
        Call<Book> updateBook(@Path("id") int bookId, @Body Book book);

        @DELETE("books/{id}/")
        Call<Book> deleteBook(@Path("id") int bookId);

        @DELETE("clean/")
        Call<ResponseBody> clean();
    }

    public static void getBooks() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        BookAPI bookapi = retrofit.create(BookAPI.class);

        Call<ResponseBody> call = bookapi.getBooks();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        BookAPI bookapi = retrofit.create(BookAPI.class);

        Call<ResponseBody> call = bookapi.getBook(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void post() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();


        BookAPI bookapi = retrofit.create(BookAPI.class);

        Book book = new Book("Shilpi Singh2", "fiction2", "The Martian2", "Penguin2");

//        Book book2 = new Book("Shilpi Singh", "fiction", "The Martian", "Penguin");
//        Call<Book> call2 = bookapi.createBook(book2);
//        call2.execute().body();

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

    public static void clean() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        System.out.println("I came here");

        BookAPI bookapi = retrofit.create(BookAPI.class);

        Call<ResponseBody> call = bookapi.clean();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.message());
                System.out.println(response.code());
                System.out.println(response.raw());
                System.out.println(response.headers());
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

   public static void main(String... args) throws IOException {
       Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
       BookAPI bookapi = retrofit.create(BookAPI.class);
//
//        // GET
//        Call<List<Book>> call = bookapi.getBooks();
//        List<Book> books = call.execute().body();
//
//        // GET ONE
//        Call<Book> call1 = bookapi.getBook(1);
//        Book book = call1.execute().body();
//
//        // POST
//       Book book2 = new Book("Shilpi Singh", "fiction", "The Martian", "Penguin");
//       Call<Book> call2 = bookapi.createBook(book2);
//       call2.execute();
//
        // PUT
//        Book book3 = new Book(null, null, "changed", null);
//        Call<Book> call3 = bookapi.updateBook(1, book3);
//        call3.execute();
//
//         // DELETE
//        Call<Book> call4 = bookapi.deleteBook(2);
//        call4.execute();
//
//        // CLEAN
//        Call<ResponseBody> call5 = bookapi.clean();
//        call5.execute();
       //  }

   }

}
