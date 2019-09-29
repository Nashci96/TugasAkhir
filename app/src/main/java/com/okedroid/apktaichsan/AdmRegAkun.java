package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class AdmRegAkun extends AppCompatActivity {

    Button bRegMhs;
    Button bRegDsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_reg_akun);

        bRegDsn = findViewById(R.id.buttonRegDsn);
        bRegMhs = findViewById(R.id.buttonRegMhs);

        bRegMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdmRegAkun.this,AdmRegAkunMhs.class);
                startActivity(intent);
            }
        });

        bRegDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdmRegAkun.this,AdmRegAkunDsn.class);
                startActivity(intent);
            }
        });
    }
}
