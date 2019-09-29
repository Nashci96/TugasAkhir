package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MhsTugasAkhir extends AppCompatActivity {

    Button bProfileTa;
    Button bSempro;
    Button bSemhas;
    Button bKompre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_tugas_akhir);

        bProfileTa  = findViewById(R.id.buttonProfileTA);
        bSempro     = findViewById(R.id.buttonSempro);
        bSemhas     = findViewById(R.id.buttonSemhas);
        bKompre     = findViewById(R.id.buttonKompre);

        bProfileTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MhsTugasAkhir.this,MhsProfileTa.class);
                startActivity(intent);
            }
        });

        bSempro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsTugasAkhir.this,MhsSempro.class);
                startActivity(intent);
            }
        });

        bSemhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsTugasAkhir.this,MhsSemhas.class);
                startActivity(intent);
            }
        });

        bKompre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MhsTugasAkhir.this,MhsKompre.class);
                startActivity(intent);
            }
        });
    }
}
