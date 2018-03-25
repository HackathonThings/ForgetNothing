package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hackbdx.DataBase.EjemploDB;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    EditText editText;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.textsitio);
        editText2 = findViewById(R.id.textnumero);
        TextView descr = findViewById(R.id.app_description);
        String description = "Do you have a trip?\n"
                + "Lets prepare your luggage!\n"
                + "This time, you will forget NOTHING";
        descr.setText(description);
        ButtonListener();
    }

    public void ButtonListener() {
        btn = findViewById(R.id.btnSubmit);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String city = editText.getText().toString();
                String count = editText2.getText().toString();
                Boolean b = false;
                Boolean m = false;
                CheckBox check = findViewById(R.id.beach);
                if (check.isChecked()) b = true;
                check = findViewById(R.id.mountain);
                if (check.isChecked()) m = true;

                Intent i = new Intent("com.example.hackbdx.TemperatureActivity");
                i.putExtra("ciutat",city);
                i.putExtra("comptador", count);
                i.putExtra("beach", b);
                i.putExtra("mountain", m);

                startActivity(i);
            }
        });
    }
}

