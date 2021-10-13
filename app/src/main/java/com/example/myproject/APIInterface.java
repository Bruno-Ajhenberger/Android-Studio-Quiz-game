package com.example.myproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api.php/")
    Call<Awdd> getUsers(@Query("amount") int amount, @Query("category") int category, @Query("type") String type);
}
