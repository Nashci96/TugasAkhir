package com.okedroid.apktaichsan;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmResetPassAkun extends AppCompatActivity {

    Button bResMhs,bResDsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_reset_pass_akun);

        bResMhs = findViewById(R.id.bResetMhs);
        bResDsn = findViewById(R.id.bResetDsn);

        bResMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdmResetPassAkun.this,AdmResMhs.class);
                startActivity(intent);
            }
        });

        bResDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmResetPassAkun.this,AdmResDsn.class);
                startActivity(intent);
            }
        });

    }
}
