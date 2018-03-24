package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    EditText editText;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonListener();
        editText = (EditText) findViewById(R.id.textsitio);
        editText2 = (EditText) findViewById(R.id.textnumero);
    }

    public void ButtonListener() {
        btn = (Button) findViewById(R.id.btnSubmit);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String dato = editText.getText().toString();
                String dato2 = editText2.getText().toString();
                Intent i = new Intent("com.example.hackbdx.TemperatureActivity");
                i.putExtra("dato",dato);
                i.putExtra("dato2", dato2);
                startActivity(i);
            }
        });
    }
}

