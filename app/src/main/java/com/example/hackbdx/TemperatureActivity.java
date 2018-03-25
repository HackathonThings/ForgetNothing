package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackbdx.network.GetTemperature;
import com.example.hackbdx.network.Main;
import com.example.hackbdx.network.TempData;
import com.example.hackbdx.CustomAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO: Al activity_temperature.xml posar ListView com a root ViewGroup
public class TemperatureActivity extends AppCompatActivity {
    private static final String KEY = "a5db0120056b7c1f3220d634b3148994";
    private static final String TAG = "package.hackbdx";
    private String n_day;
    private String ciutat;
    public float temperatura;
    public float humidity;
    public float pressure;
    public ListView listView;
    public String[] llista;
    public Boolean beach_list = false;
    public Boolean mountain_list = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            ciutat = (String)extras.get("ciutat");
            n_day = extras.getString("comptador");
            beach_list = extras.getBoolean("beach");
            mountain_list = extras.getBoolean("mountain");
        }

        listView = findViewById(R.id.listView);
        inicialitzaLlista();
        CustomAdapter customAdapter = new CustomAdapter(TemperatureActivity.this, llista);
        listView.setAdapter(customAdapter);

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
                                      /*TextView t = (TextView) findViewById(R.id.temp);
                                      t.setText(Float.toString(temperatura));

                                      TextView t3 = (TextView) findViewById(R.id.humidity);
                                      t3.setText(Float.toString(humidity));

                                      TextView t4 = (TextView) findViewById(R.id.pressure);
                                      t4.setText(Float.toString(pressure));*/

                                  }
                              }
                );
            }
        });
    }

    public void inicialitzaLlista() {


        ArrayList<String> mllista = new ArrayList<String>();
        if (temperatura < 15 && temperatura > 5) {
            mllista.add("Jacket");
        }
        else if(temperatura < 5) {
            mllista.add("Big Warm Jacket");
            mllista.add("Gloves");
            mllista.add("Scarf");
        }
        if(beach_list) {
            mllista.add("Towel");
            mllista.add("Flip-Flops");
            mllista.add("Suncream");
            mllista.add("Sunglasses");
            mllista.add("Swimwear");
        }
        if(mountain_list) {
            mllista.add("Stick");
            mllista.add("Snacks");
            mllista.add("Mountain boots");
        }

        mllista.add("Underpants");
        mllista.add("Socks");
        mllista.add("T-shirt");
        mllista.add("Trousers");
        mllista.add("Deodorant");
        mllista.add("Toothbrush");
        mllista.add("Toothpaste");
        mllista.add("Umbrella");
        mllista.add("Mobile charger");
        mllista.add("Snacks");
        mllista.add("Water");

        llista = new String[mllista.size()];
        llista = mllista.toArray(llista);
    }
}
