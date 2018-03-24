package com.example.hackbdx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackbdx.network.GetTemperature;
import com.example.hackbdx.network.Main;
import com.example.hackbdx.network.TempData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TemperatureActivity extends AppCompatActivity {

    private static final String KEY = "a5db0120056b7c1f3220d634b3148994";
    private static final String TAG = "package.hackbdx";
    private String n_day;
    private String ciutat = "London";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        cridaRetrofit();
        Log.d(TAG, "Torno a ser al main jeje");
    }

    public void cridaRetrofit() {
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
                    Main a = resp.getMain();
                    t.setText(a.getTemp());
                    p.setText(a.getPressure());
                    h.setText(a.getHumidity());
                    tmin.setText(a.getTemp_min());
                    tmax.setText(a.getTemp_max());
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
    }
}
