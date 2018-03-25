package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hackbdx.network.TempData;
import com.example.hackbdx.CustomAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class TemperatureActivity extends AppCompatActivity {
    private static final String KEY = "a5db0120056b7c1f3220d634b3148994";
    private static final String TAG = "package.hackbdx";
    private float n_day;
    private String ciutat;
    public float temperatura;
    public float humidity;
    public float pressure;
    public ListView listView;
    public Item[] llista;
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
            n_day = Float.parseFloat(extras.getString("comptador"));
            beach_list = extras.getBoolean("beach");
            mountain_list = extras.getBoolean("mountain");
        }

        listView = findViewById(R.id.listView);
        inicialitzaLlista();
        CustomAdapter customAdapter = new CustomAdapter(TemperatureActivity.this, llista);
        listView.setAdapter(customAdapter);

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
                                      TextView t = findViewById(R.id.descrip_temp);
                                      String s = t.getText() + String.format("%.2f",temperatura);
                                      t.setText(s);
                                  }
                              }
                );
            }
        });
    }

    public void inicialitzaLlista() {
        ArrayList<Item> mllista = new ArrayList<Item>();
        if (temperatura < 15 && temperatura > 5) {
            mllista.add(new Item("Jacket", 1));
        }
        else if(temperatura < 5) {
            mllista.add(new Item("Big Warm Jacket", 1));
            mllista.add(new Item("Gloves", 1));
            mllista.add(new Item("Scarf", 1));
        }
        if(beach_list) {
            mllista.add(new Item("Towel", 1));
            mllista.add(new Item("Flip-Flops", 1));
            mllista.add(new Item("Suncream", 1));
            mllista.add(new Item("Sunglasses", 1));
            mllista.add(new Item("Swimwear", n_day));
        }
        if(mountain_list) {
            mllista.add(new Item("Stick", 1));
            mllista.add(new Item("Mountain boots", 1));
        }

        mllista.add(new Item("Underpants", n_day));
        mllista.add(new Item("Socks", n_day));
        mllista.add(new Item("T-shirt", n_day));
        mllista.add(new Item("Trousers", n_day));
        mllista.add(new Item("Deodorant", 1));
        mllista.add(new Item("Toothbrush", 1));
        mllista.add(new Item("Toothpaste", 1));
        mllista.add(new Item("Book", n_day/15));
        mllista.add(new Item("Umbrella", 1));
        mllista.add(new Item("Mobile charger", 1));

        llista = new Item[mllista.size()];
        llista = mllista.toArray(llista);
    }
}
