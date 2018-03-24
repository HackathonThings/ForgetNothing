package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity {
    TextView texto, texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        texto = (TextView) findViewById(R.id.text);
        texto2 = (TextView) findViewById(R.id.text2);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            String dato = (String)extras.get("dato");
            String dato2 = extras.getString("dato2");
            texto.setText(dato);
            texto2.setText(dato2);
        }

    }
}
