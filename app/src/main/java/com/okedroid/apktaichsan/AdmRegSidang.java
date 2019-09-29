package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmRegSidang extends AppCompatActivity {

    Button bRegSempro,bRegSemhas,bRegKompre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_reg_sidang);

        bRegSempro = findViewById(R.id.bVerSempro);
        bRegSemhas = findViewById(R.id.bVerSemhas);
        bRegKompre = findViewById(R.id.bVerKompre);

        bRegSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmRegSidang.this,AdmRegSempro.class);
                startActivity(intent);
            }
        });

        bRegSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmRegSidang.this,AdmRegSemhas.class);
                startActivity(intent);
            }
        });

        bRegKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmRegSidang.this,AdmRegKompre.class);
                startActivity(intent);
            }
        });
    }
}
