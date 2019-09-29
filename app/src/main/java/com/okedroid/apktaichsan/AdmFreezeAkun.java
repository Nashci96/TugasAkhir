package com.okedroid.apktaichsan;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmFreezeAkun extends AppCompatActivity {

    Button bFmhs,bFdsn,bAmhs,bAdsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_freeze_akun);

        bFmhs = findViewById(R.id.bFrezMhs);
        bFdsn = findViewById(R.id.bFrezDsn);
        bAmhs = findViewById(R.id.bActMhs);
        bAdsn = findViewById(R.id.bActDsn);

        bFmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmFreezeAkun.this,AdmFAkunMhs.class);
                startActivity(intent);
            }
        });

        bFdsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmFreezeAkun.this, AdmFAkunDsn.class);
                startActivity(intent);
            }
        });

        bAmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmFreezeAkun.this,AdmAAkunMhs.class);
                startActivity(intent);
            }
        });

        bAdsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmFreezeAkun.this,AdmAAkunDsn.class);
                startActivity(intent);
            }
        });

    }
}
