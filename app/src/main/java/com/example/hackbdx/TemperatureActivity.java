package com.example.hackbdx;

import android.content.Intent;
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
    private String ciutat;
    float temperatura;
    float humidity;
    float pressure;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            ciutat = (String)extras.get("ciutat");
            n_day = extras.getString("comptador");
        }
        Log.d(TAG, "Torno a ser al main jeje");
        try {
            run("https://api.openweathermap.org/data/2.5/forecast?q=" + ciutat + "&appid=a5db0120056b7c1f3220d634b3148994");

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
                humidity = Float.parseFloat(res.list.get(0).main.humidity);
                pressure = Float.parseFloat(res.list.get(0).main.pressure);


                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      TextView t = (TextView) findViewById(R.id.temp);
                                      t.setText(Float.toString(temperatura));

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
}
