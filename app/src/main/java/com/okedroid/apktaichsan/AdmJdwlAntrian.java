package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmJdwlAntrian extends AppCompatActivity {

    Button bJdwlSempro , bJdwlSemhas , bJdwlKompre ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_jdwl_antrian);

        bJdwlSempro = findViewById(R.id.bAdmJdwlSempro);
        bJdwlSemhas = findViewById(R.id.bAdmJdwlSemhas);
        bJdwlKompre = findViewById(R.id.bAdmJdwlKompre);

        bJdwlSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmJdwlAntrian.this,KpdListSempro.class);
                startActivity(intent);
            }
        });

        bJdwlSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmJdwlAntrian.this,KpdListSemhas.class);
                startActivity(intent);
            }
        });

        bJdwlKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmJdwlAntrian.this,KpdListKompre.class);
                startActivity(intent);
            }
        });

    }
}
