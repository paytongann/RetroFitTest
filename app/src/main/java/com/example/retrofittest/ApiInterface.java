package com.example.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://www.googleapis.com/books/v1/volumes?
    @GET("v1/volumes?")
    Call<BookPojo> getBooks(@Query("q")String title,
                            @Query("maxResults")String results,
                            @Query("printType")String type);
}