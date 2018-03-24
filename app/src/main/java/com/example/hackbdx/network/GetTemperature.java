package com.example.hackbdx.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by canom on 24/3/2018.
 */

public interface GetTemperature {
    String URL = "https://api.openweathermap.org/";

    @GET("/data/2.5/forecast")
    Call<TempData> crida(@Query("appid") String key, @Query("q") String nciutat);
}
