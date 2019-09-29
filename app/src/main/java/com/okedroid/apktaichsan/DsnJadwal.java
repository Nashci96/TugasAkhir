package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

public class DsnJadwal extends AppCompatActivity {

    Button bInputJdwlDsn;
    Button bJdwlSeminarDsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsn_jadwal);

        bInputJdwlDsn   = findViewById(R.id.buttonInputJdwlDsn);
        bJdwlSeminarDsn = findViewById(R.id.buttonJadwalSeminarDsn);

        bInputJdwlDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsnJadwal.this,DsnInputJdwl.class);
                startActivity(intent);
            }
        });

        bJdwlSeminarDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DsnJadwal.this,DsnJdwlSeminarSidang.class);
                startActivity(intent);
            }
        });
    }
}
