package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DsnJdwlSeminarSidang extends AppCompatActivity {

    Button bJdwlSempro,bJdwlSemhas,bJdwlKompre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_jdwl_seminar_sidang);

        bJdwlSempro = findViewById(R.id.bDsnJdwlSempro);
        bJdwlSemhas = findViewById(R.id.bDsnJdwlSemhas);
        bJdwlKompre = findViewById(R.id.bDsnJdwlKompre);

        bJdwlSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnJdwlSeminarSidang.this,DsnSempro.class);
                startActivity(intent);
            }
        });

        bJdwlSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnJdwlSeminarSidang.this,DsnSemhas.class);
                startActivity(intent);
            }
        });

        bJdwlKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnJdwlSeminarSidang.this,DsnKompre.class);
                startActivity(intent);
            }
        });
    }
}
