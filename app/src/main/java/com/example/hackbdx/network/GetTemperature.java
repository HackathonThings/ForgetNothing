package com.example.hackbdx.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by canom on 24/3/2018.
 */

public interface GetTemperature {
    String URL = "api.openweathermap.org/";

    @GET("/data/2.5/forecast")
    Call<TempData> crida(@Query("q") String nom_ciutat, @Query("appid") String key);
}
