package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KpdJdwlAntrianSidang extends AppCompatActivity {

    Button bJdwlSempro,bJdwlSemhas,bJdwlKompre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpd_jdwl_antrian_sidang);

        bJdwlSempro= findViewById(R.id.bJdwlSempro);
        bJdwlSemhas= findViewById(R.id.bJdwlSemhas);
        bJdwlKompre= findViewById(R.id.bJdwlKompre);

        bJdwlSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KpdJdwlAntrianSidang.this,KpdListSempro.class);
                startActivity(intent);
            }
        });

        bJdwlSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KpdJdwlAntrianSidang.this,KpdListSemhas.class);
                startActivity(intent);
            }
        });

        bJdwlKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KpdJdwlAntrianSidang.this,KpdListKompre.class);
                startActivity(intent);
            }
        });
    }
}
