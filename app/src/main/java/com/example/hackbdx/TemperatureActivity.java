package com.example.hackbdx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackbdx.network.GetTemperature;
import com.example.hackbdx.network.Main;
import com.example.hackbdx.network.TempData;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TemperatureActivity extends AppCompatActivity {

    private static final String KEY = "a5db0120056b7c1f3220d634b3148994";
    private static final String TAG = "package.hackbdx";
    private String n_day;
    private String ciutat = "London";
    float temperatura;
    float temp_min;
    float temp_max;
    float humidity;
    float pressure;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
       // cridaRetrofit();
        Log.d(TAG, "Torno a ser al main jeje");
        try {
            run("https://api.openweathermap.org/data/2.5/forecast?q=London&appid=a5db0120056b7c1f3220d634b3148994");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OkHttpClient client = new OkHttpClient();

    void run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String a = response.body().string();
                Log.d("onResponse", a);
                final TempData res = new Gson().fromJson(a, TempData.class);
                temperatura = Float.parseFloat(res.list.get(0).main.temp) - 273;
                temp_min = Float.parseFloat(res.list.get(0).main.temp_min) - 273;
                temp_max = Float.parseFloat(res.list.get(0).main.temp_max) - 273;
                humidity = Float.parseFloat(res.list.get(0).main.humidity);
                pressure = Float.parseFloat(res.list.get(0).main.pressure);


                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      TextView t = (TextView) findViewById(R.id.temp);
                                      t.setText(Float.toString(temperatura));

                                      TextView t1 = (TextView) findViewById(R.id.tempMin);
                                      t1.setText(Float.toString(temp_min));

                                      TextView t2 = (TextView) findViewById(R.id.tempMax);
                                      t2.setText(Float.toString(temp_max));

                                      TextView t3 = (TextView) findViewById(R.id.humidity);
                                      t3.setText(Float.toString(humidity));

                                      TextView t4 = (TextView) findViewById(R.id.pressure);
                                      t4.setText(Float.toString(pressure));
                                  }
                              }

                );
            }
        });
    }

   /* public void cridaRetrofit() {
        Log.d(TAG, "abans retrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetTemperature.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetTemperature client = retrofit.create(GetTemperature.class);
        Call<TempData> call = client.crida(KEY, ciutat);

        Log.d(TAG, "abans enqueue");

        call.enqueue(new Callback<TempData>() {
            @Override
            public void onResponse(Call<TempData> call, Response<TempData> response) {

                Log.d(TAG, "dins try");
                TextView t = findViewById(R.id.temp);
                TextView p = findViewById(R.id.pressure);
                TextView h = findViewById(R.id.humidity);
                TextView tmin = findViewById(R.id.tempMin);
                TextView tmax = findViewById(R.id.tempMax);

                if(response.isSuccessful()) {
                    TempData resp = response.body();
                    Log.d(TAG, "dins if del try");
                    Main a = resp.main;
                    t.setText(a.temp);
                    p.setText(a.pressure);
                    h.setText(a.humidity);
                    tmin.setText(a.temp_min);
                    tmax.setText(a.temp_max);
                    Log.d(TAG, "tots els textviews fets");
                    Log.d(TAG, "dins else del try");
                    Toast to = Toast.makeText(TemperatureActivity.this, "Conectat", Toast.LENGTH_SHORT);
                    to.show();
                }
                else {
                    int statusCode = response.code();
                    Toast to = Toast.makeText(TemperatureActivity.this, statusCode, Toast.LENGTH_SHORT);
                    to.show();
                }



                Log.d(TAG, "en principi ja puto estic");


            }

            @Override
            public void onFailure(Call<TempData> call, Throwable t) {
                Toast to = Toast.makeText(TemperatureActivity.this, "Fail to connect to the server", Toast.LENGTH_SHORT);
                to.show();
                Log.d("onFailure", "error loading from API");
            }
        });
    }*/
}
