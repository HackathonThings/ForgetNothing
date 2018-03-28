package com.example.hackbdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hackbdx.DataBase.EjemploDB;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    public EditText editText;
    public EditText editText2;
    public int gender_info = 1; //1 = unisex, 2 = man, 3 = woman
    public ImageView gender;


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
        genderIconListener();
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
                Boolean p = false;
                CheckBox check = findViewById(R.id.beach);
                if (check.isChecked()) b = true;
                check = findViewById(R.id.mountain);
                if (check.isChecked()) m = true;
                check = findViewById(R.id.party);
                if (check.isChecked()) p = true;

                Intent i = new Intent("com.example.hackbdx.TemperatureActivity");
                i.putExtra("ciutat",city);
                i.putExtra("comptador", count);
                i.putExtra("beach", b);
                i.putExtra("mountain", m);
                i.putExtra("party", p);

                startActivity(i);
            }
        });
    }

    public void genderIconListener() {
        gender = (ImageView) findViewById(R.id.gender);
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender_info == 1) {
                    gender_info = 2;
                    gender.setImageResource(R.drawable.man);
                }
                else if (gender_info == 2) {
                    gender_info = 3;
                    gender.setImageResource(R.drawable.woman);
                }
                else {
                    gender_info = 1;
                    gender.setImageResource(R.drawable.unisex);
                }
            }
        });
    }
}

