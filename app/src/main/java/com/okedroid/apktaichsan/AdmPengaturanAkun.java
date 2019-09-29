package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class AdmPengaturanAkun extends AppCompatActivity {

    Button bResetPassAkun;
    Button bFreezeAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_pengaturan_akun);

        bFreezeAkun     = findViewById(R.id.buttonFreezeAkun);
        bResetPassAkun  = findViewById(R.id.buttonResetPasswordAkun);

        bFreezeAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdmPengaturanAkun.this,AdmFreezeAkun.class);
                startActivity(intent);
            }
        });

        bResetPassAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdmPengaturanAkun.this,AdmResetPassAkun.class);
                startActivity(intent);
            }
        });
    }
}
